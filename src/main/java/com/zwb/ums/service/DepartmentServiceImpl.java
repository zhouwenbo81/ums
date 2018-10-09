package com.zwb.ums.service;

import com.entcms.sdk.BaseServiceImpl;
import com.entcms.sdk.HandleUtil;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.SplitPage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.mapper.DepartmentMapper;
import com.zwb.ums.mapper.RelationMapper;
import com.zwb.ums.model.po.Department;
import com.zwb.ums.model.po.Relation;
import com.zwb.ums.model.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Title: DepartmentServiceImpl
 * </p>
 * <p>
 * Description: 部门Service接口实现类
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/7 11:34
 */
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private RelationMapper relationMapper;

    /**
     * <p>Title: queryDepartmentByParams</p>
     * <p>Description: 根据自定义参数对象查询部门信息</p>
     * @param departmentVO
     * @return
     * @throws ENTCMSException
     * @see com.zwb.ums.service.DepartmentService#queryDepartmentByParams(com.zwb.ums.model.vo.DepartmentVO)
     */
    @Override
    public ReturnMessage<Department> queryDepartmentByParams(DepartmentVO departmentVO) throws ENTCMSException {
        List<Department> list = departmentMapper.selectByParams(departmentVO);
        return new ReturnMessage<Department>("100", "查询成功！", list);
    }

    @Override
    public ReturnMessage<Department> fdeleteDepartmentByIds(String[] ids) throws ENTCMSException {
        int count = 0;
        Department department = new Department();
        department.setDepartmentState("10");
        for (String id: ids) {
            department.setDepartmentId(id);
            int i = departmentMapper.updateByPrimaryKeySelective(department);
            if(i == 1){
                count++;
            }
        }
        return new ReturnMessage<>("100","删除成功",count);
    }

    @Override
    public ReturnMessage<Department> fdeleteDepartmentById(String departmentId) throws ENTCMSException {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentState("10");
        int i = departmentMapper.updateByPrimaryKeySelective(department);
        if(i == 1){
            return new ReturnMessage<>("100","删除成功");
        }
        return new ReturnMessage<>("999","删除失败");
    }

    @Override
    public ReturnMessage<Department> queryDepartmentList(DepartmentVO departmentVO) throws ENTCMSException {
        int rowCount = departmentMapper.selectAllObjCount(departmentVO);
        List<Department> list = departmentMapper.selectAllObj(departmentVO);
        SplitPage splitPage = departmentVO.getSplitPage();
        if(list.size()==0 && splitPage.getPageNum() > 1 ){
            splitPage.setPageNum(splitPage.getPageNum() -1);
            list = departmentMapper.selectAllObj(departmentVO);
        }
        splitPage.setRowCount(rowCount);
        departmentVO.setSplitPage(splitPage);
        return new ReturnMessage<Department>("100","查询成功",list,splitPage);
    }

    @Override
    public ReturnMessage<Department> addDepartment(Department department, String companyId) throws ENTCMSException {
        String departmentId = HandleUtil.UUID32();
        department.setDepartmentId(departmentId);
        department.setCreateTime(new Date());
        //添加公司关联
        addRelation(companyId,departmentId);
        int i = departmentMapper.insert(department);
        if(i == 1){
            return new ReturnMessage<>("100","添加部门成功");
        }
        throw new RuntimeException("添加部门失败");
    }

    @Override
    public ReturnMessage<Department> queryDepartmentById(String departmentId) throws ENTCMSException {
        Department department = departmentMapper.selectDepartmentById(departmentId);
        if(department !=null){
            return new ReturnMessage<Department>("100","查询成功" ,department);
        }
        return new ReturnMessage<Department>("999","查询失败" );
    }

    //添加公司部门关联
    private boolean addRelation(String companyId, String departmentId) {
        Relation relation = new Relation();
        relation.setRelationId(HandleUtil.UUID32());
        relation.setCompanyId(companyId);
        relation.setDepartmentId(departmentId);
        relation.setRelationType("00");
        int i = relationMapper.insert(relation);
        if (i == 1) {
            return true;
        }
        throw new RuntimeException("添加公司部门关联失败！");
    }

    @Override
    public ReturnMessage<Department> modifyDepartment(DepartmentVO department) throws ENTCMSException {
        Relation relation = new Relation();
        DepartmentVO departmentParam = new DepartmentVO();
        departmentParam.setDepartmentName(department.getDepartmentName());
        String companyId = department.getCompanyId() != null ? department.getCompanyId() : "";
        departmentParam.setCompanyId(companyId);
        if(companyId != null && !"".equals(companyId)){
            //通过公司和部门名称查询 修改后的公司是否拥有该部门
            List<Department> list = departmentMapper.selectByParams(departmentParam);
            if(list.size()>0){//当前名称的部门已存在
                return new ReturnMessage<Department>("101","所选公司已存在该部门！");
            }
        }
        //修改公司部门关联
        relation.setRelationId(department.getRelationId());
        relation.setCompanyId(companyId);
        relation.setDepartmentId(department.getDepartmentId());
        relation.setRelationType("00");
        modifyRelation(relation);
        int i = departmentMapper.updateByPrimaryKeySelective(department);
        if (i == 1) {
            return new ReturnMessage<Department>("100", "修改部门成功！");
        }
        throw new RuntimeException("修改部门失败！");
    }
    //修改公司部门关联
    private boolean modifyRelation(Relation relation) {
        int i = relationMapper.updateByPrimaryKeySelective(relation);
        if (i == 1) {
            return true;
        }
        throw new RuntimeException("添加公司部门关联失败！");
    }

    /**
     * <p>Title: queryDepartmentByCompanyIdAndRelationType</p>
     * <p>Description: 根据公司Id和关联类型查询该公司的所有部门</p>
     * @param companyId 公司Id
     * @return
     * @throws ENTCMSException
     * @see com.zwb.ums.service.DepartmentService#queryDepartmentByCompanyIdAndRelationType(java.lang.String)
     */
    @Override
    public ReturnMessage<Department> queryDepartmentByCompanyIdAndRelationType(String companyId) throws ENTCMSException {
        List<Department> list = departmentMapper.selectByCompanyIdAndRelationType(companyId);
        return new ReturnMessage<Department>("100", "查询成功！", list);
    }


}
