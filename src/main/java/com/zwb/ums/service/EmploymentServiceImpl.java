package com.zwb.ums.service;

import com.entcms.sdk.BaseServiceImpl;
import com.entcms.sdk.HandleUtil;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.SplitPage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.mapper.EmploymentMapper;
import com.zwb.ums.mapper.RelationMapper;
import com.zwb.ums.model.po.Employment;
import com.zwb.ums.model.po.Relation;
import com.zwb.ums.model.vo.EmploymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Title: EmploymentServiceImpl
 * </p>
 * <p>
 * Description: 职位Service接口实现类
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/7 13:47
 */
@Service("employmentService")
public class EmploymentServiceImpl extends BaseServiceImpl<Employment> implements EmploymentService {

    @Autowired
    private EmploymentMapper employmentMapper;

    @Autowired
    private RelationMapper relationMapper;

    @Override
    public ReturnMessage<Employment> fdeleteEmploymentById(String employmentId) throws ENTCMSException {
        Employment employment = new Employment();
        employment.setEmploymentState("10");
        employment.setEmploymentId(employmentId);
        int i = employmentMapper.updateByPrimaryKeySelective(employment);
        if(i == 1){
            return new ReturnMessage<>("100","删除成功");
        }
        return new ReturnMessage<>("999","删除失败");
    }

    @Override
    public ReturnMessage<Employment> fdeleteEmploymentByIds(String[] employmentIds) throws ENTCMSException {
        int count = 0;
        Employment employment = new Employment();
        employment.setEmploymentState("10");
        for (String employmentId:employmentIds) {
            employment.setEmploymentId(employmentId);
            int i = employmentMapper.updateByPrimaryKeySelective(employment);
            if(i==1){
                count++;
            }
        }
        return new ReturnMessage<>("100","删除成功",count);
    }

    @Override
    public ReturnMessage<EmploymentVO> queryEmploymentList(EmploymentVO employmentVO) throws ENTCMSException {
        int rowCount = employmentMapper.selectAllObjCount(employmentVO);
        List<EmploymentVO> list = employmentMapper.selectAllObj(employmentVO);
        SplitPage splitPage = employmentVO.getSplitPage();
        if(list.size() == 0 && splitPage.getPageNum() > 1){
            splitPage.setPageNum(splitPage.getPageNum() - 1);
            list = employmentMapper.selectAllObj(employmentVO);
        }
        splitPage.setRowCount(rowCount);
        employmentVO.setSplitPage(splitPage);
        return new ReturnMessage<>("100","查询成功",list,splitPage);
    }

    @Override
    public ReturnMessage<Employment> queryAllEmployment(Employment employment) throws ENTCMSException {
        List<Employment> list = employmentMapper.selectAllEmployment(employment);
        if(list.size()>0){
            return new ReturnMessage<>("100","查询成功",list);
        }
        return new ReturnMessage<>("999","查询失败");
    }

    @Override
    public ReturnMessage<EmploymentVO> queryEmploymentById(String employmentId) throws ENTCMSException {
        EmploymentVO employmentVO = employmentMapper.selectEmploymentById(employmentId);
        if(employmentVO != null){
            return new ReturnMessage<>("100","查询成功",employmentVO);
        }
        return new ReturnMessage<>("999","查询失败");
    }

    @Override
    public ReturnMessage<Employment> queryEmploymentByDepartmentIdAndRelationType(String departmentId) throws ENTCMSException {
        List<Employment> list = employmentMapper.selectByDepartmentIdAndRelationType(departmentId);
        if(list.size()>0){
            return new ReturnMessage<>("100","查询成功",list);
        }
        return new ReturnMessage<>("999","查询失败",list);
    }

    @Override
    public ReturnMessage<Employment> addEmployment(Employment employment, String companyId, String departmentId) throws ENTCMSException {
        String employmentId = employment.getEmploymentId();
        if(HandleUtil.isNull(employmentId)){
            employmentId = HandleUtil.UUID32();
        }
        // 添加公司部门职位关联
        addRelation(companyId, departmentId, employmentId);
        employment.setEmploymentId(employmentId);
        int i = employmentMapper.insert(employment);
        if (i == 1) {
            return new ReturnMessage<Employment>("100", "添加职位成功！");
        }
        throw new RuntimeException("添加职位失败！");
    }

    /**
     * 添加公司部门职位关联
     * @param companyId
     * @param departmentId
     * @param employmentId
     * @return
     */
    private boolean addRelation(String companyId, String departmentId, String employmentId) {
        Relation relation = new Relation();
        relation.setRelationId(HandleUtil.UUID32());
        relation.setCompanyId(companyId);
        relation.setDepartmentId(departmentId);
        relation.setEmploymentId(employmentId);
        relation.setRelationType("01");
        int i = relationMapper.insert(relation);
        if (i == 1) {
            return true;
        }
        throw new RuntimeException("添加公司部门职位关联失败！");
    }

    @Override
    public ReturnMessage<Employment> modifyEmployment(EmploymentVO employmentVO) throws ENTCMSException {
        Relation relation = new Relation();
        relation.setRelationId(employmentVO.getRelationId());
        relation.setCompanyId(employmentVO.getCompanyId());
        relation.setDepartmentId(employmentVO.getDepartmentId());
        relation.setRelationType("01");
        Employment employment = employmentVO;
        // 修改公司部门职位关联
        modifyRelation(relation);
        int i = employmentMapper.updateByPrimaryKeySelective(employment);
        if (i == 1) {
            return new ReturnMessage<Employment>("100", "修改职位成功！");
        }
        throw new RuntimeException("修改职位失败！");
    }

    /**
     * 修改公司部门职位关联
     * @param relation
     * @return
     */
    private boolean modifyRelation(Relation relation) {
        int i = relationMapper.updateByPrimaryKeySelective(relation);
        if (i == 1) {
            return true;
        }
        throw new RuntimeException("添加公司部门关联失败！");
    }
}
