package com.zwb.ums.controller;

import com.entcms.sdk.HandleUtil;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.SplitPage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.model.po.Company;
import com.zwb.ums.model.po.Department;
import com.zwb.ums.model.vo.DepartmentVO;
import com.zwb.ums.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: ApiDepartmentController
 * </p>
 * <p>
 * Description: 部门Controller
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/7 14:02
 */
@Controller
public class ApiDepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * @Title: addDepartment
     * @Description: 添加部门
     * @param department
     * @return
     * @author zhouwenbo
     * @date 2018年4月27日 下午4:02:36
     * @version 1.0
     */
    @RequestMapping("/api/department/add")
    @ResponseBody
    public Map<String, Object> addDepartment(@RequestBody DepartmentVO department) {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        String companyId = department.getCompanyId();
        try {
            //后台校验
            if(StringUtils.isEmpty(department.getDepartmentName())){
                rootMap.put("code", "999");
                rootMap.put("mesg", "部门名称不能为空");
                return rootMap;
            }
            Date date = new Date();
            department.setDepartmentId(HandleUtil.UUID32());
            department.setCreateTime(date);
            //查询部门是否已存在
            ReturnMessage<Department> department_RMG = departmentService.queryDepartmentByParams(department);
            List<Department> list = department_RMG.getList();
            if(list.size()>0){//当前名称的部门已存在
                rootMap.put("code", "101");
                rootMap.put("mesg", "部门信息已存在，请勿重复添加！");
                return rootMap;
            }
//            ReturnMessage<Department> returnMessage = departmentService.addObject(department);
            ReturnMessage<Department> returnMessage = departmentService.addDepartment(department,companyId);
            if("999".equals(returnMessage.getCode())){
                rootMap.put("code", "999");
                rootMap.put("mesg", "添加数据失败！");
                return rootMap;
            }
            rootMap.put("code", returnMessage.getCode());
            rootMap.put("mesg", returnMessage.getMesg());
            return rootMap;
        } catch (Exception e) {
            e.printStackTrace();
            rootMap.put("code", "999");
            rootMap.put("mesg", e.getMessage());
            return rootMap;
        }
    }

    /**
     * @Title: queryDepartmentListAndPage
     * @Description: 分页查询部门信息列表
     * @param: departmentVO
     * @param: pageNum
     * @param: pageSize
     * @return:
     * @author zhouwenbo
     * @date 2018/5/7 14:12
     * @version 1.0
     */
    @RequestMapping("/api/department/query/list/{pageNum}")
    @ResponseBody
    public Map<String, Object> queryDepartmentListAndPage(@RequestBody DepartmentVO departmentVO, @PathVariable("pageNum")int pageNum,
                  @RequestParam(value="pageSize",defaultValue="10")int pageSize) throws ENTCMSException {
        Map<String, Object> rootMap = new HashMap<String,Object>();
        SplitPage splitPage = new SplitPage();
        splitPage.setPageNum(pageNum);
        splitPage.setPageSize(pageSize);
        departmentVO.setSplitPage(splitPage);
        ReturnMessage<Department> returnMessage = departmentService.queryDepartmentList(departmentVO);
        if("999".equals(returnMessage.getCode())){
            rootMap.put("code", "999");
            rootMap.put("mesg", "查询失败！");
            return rootMap;
        }
        rootMap.put("code", returnMessage.getCode());
        rootMap.put("mesg", returnMessage.getMesg());
        rootMap.put("data", returnMessage.getList() !=null ? returnMessage.getList() : "");
        rootMap.put("pageNum", returnMessage.getSplitPage() !=null ? returnMessage.getSplitPage().getPageNum() : "");
        rootMap.put("pageSize", returnMessage.getSplitPage() !=null ? returnMessage.getSplitPage().getPageSize() : "");
        rootMap.put("pageCount", returnMessage.getSplitPage() !=null ? returnMessage.getSplitPage().getRowCount() : "");
        return rootMap;
    }

    /**
     * @Title: deleteDepartment
     * @Description: 根据部门Id删除部门信息 (真删除)
     * @param: id 部门Id
     * @return:
     * @author zhouwenbo
     * @date 2018/5/7 14:17
     * @version 1.0
     */
    @RequestMapping("/api/department/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteDepartment(@PathVariable("id") String id){
        Map<String, Object> rootMap = new HashMap<String,Object>();
        try {
            ReturnMessage<Department> returnMessage = departmentService.deleteObjectById(id);
            if("999".equals(returnMessage.getCode())){
                rootMap.put("code", "999");
                rootMap.put("mesg", "删除失败");
                return rootMap;
            }
            rootMap.put("code", "100");
            rootMap.put("mesg", "删除成功");
            return rootMap;
        } catch (Exception e) {
            e.printStackTrace();
            rootMap.put("code", "999");
            rootMap.put("mesg", e.getMessage());
            return rootMap;
        }
    }

    /**
     * @Title: fdeleteDepartment
     * @Description: 根据部门Id删除部门信息 (软删除)
     * @param: id 部门Id
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 14:18
     * @version 1.0
     */
    @RequestMapping("/api/department/fdelete/{id}")
    @ResponseBody
    public Map<String, Object> fdeleteDepartment(@PathVariable("id") String id){
        Map<String, Object> rootMap = new HashMap<String,Object>();
        try {
            ReturnMessage<Department> returnMessage = departmentService.fdeleteDepartmentById(id);
            if("999".equals(returnMessage.getCode())){
                rootMap.put("code", "999");
                rootMap.put("mesg", "删除失败");
                return rootMap;
            }
            rootMap.put("code", "100");
            rootMap.put("mesg", "删除成功");
            return rootMap;
        } catch (Exception e) {
            e.printStackTrace();
            rootMap.put("code", "999");
            rootMap.put("mesg", e.getMessage());
            return rootMap;
        }
    }

    /**
     * @Title: fdeleteDepartmentByIds
     * @Description: 批量软删除部门信息
     * @param: ids
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 14:22
     * @version 1.0
     */
    @RequestMapping("/api/department/fdelete/all")
    @ResponseBody
    public Map<String, Object> fdeleteDepartmentByIds(@RequestBody String[] ids) throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
        ReturnMessage<Department> returnMessage = departmentService.fdeleteDepartmentByIds(ids);
        if("100".equals(returnMessage.getCode())){
            rootMap.put("code", "100");
            rootMap.put("mesg", "删除成功");
            return rootMap;
        }
        rootMap.put("code", "999");
        rootMap.put("mesg", "删除失败");
        return rootMap;
    }

    /**
     * @Title: queryDepartmentById
     * @Description: 根据Id查询部门信息
     * @param: id
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 14:23
     * @version 1.0
     */
    @RequestMapping("/api/department/queryone/{id}")
    @ResponseBody
    public Map<String, Object> queryDepartmentById(@PathVariable("id") String id) throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
        ReturnMessage<Department> returnMessage = departmentService.queryDepartmentById(id);
        if("999".equals(returnMessage.getCode())){
            rootMap.put("code", "999");
            rootMap.put("mesg", "查询错误");
            return rootMap;
        }
        rootMap.put("code", returnMessage.getCode());
        rootMap.put("mesg", returnMessage.getMesg());
        rootMap.put("data", returnMessage.getObject());
        return rootMap;
    }

    /**
     * @Title: modifyDepartment
     * @Description: 修改部门信息
     * @param: department 部门信息
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 14:29
     * @version 1.0
     */
    @RequestMapping("/api/department/modify")
    @ResponseBody
    public Map<String, Object> modifyDepartment(@RequestBody DepartmentVO department)throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
//        ReturnMessage<Department> returnMessage = departmentService.updateObjectByPrimaryKeySelective(department);
        ReturnMessage<Department> returnMessage = departmentService.modifyDepartment(department);
        if("999".equals(returnMessage.getCode())){
            rootMap.put("code", "999");
            rootMap.put("mesg", "修改失败！");
            return rootMap;
        }
        rootMap.put("code", returnMessage.getCode());
        rootMap.put("mesg", returnMessage.getMesg());
        return rootMap;
    }

    /**
     * @Title: modifyDepartmentState
     * @Description: 修改部门状态
     * @param: 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/9 13:08
     * @version 1.0
     */
    @RequestMapping("/api/department/modify/state")
    @ResponseBody
    public Map<String, Object> modifyDepartmentState(@RequestBody Department department)throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
        ReturnMessage<Department> returnMessage = departmentService.updateObjectByPrimaryKeySelective(department);
        if("999".equals(returnMessage.getCode())){
            rootMap.put("code", "999");
            rootMap.put("mesg", "修改失败！");
            return rootMap;
        }
        rootMap.put("code", returnMessage.getCode());
        rootMap.put("mesg", returnMessage.getMesg());
        return rootMap;
    }

    @RequestMapping("/api/department/load/{companyId}")
    @ResponseBody
    public Map<String, Object> queryAllLoadDepartment(@PathVariable("companyId") String companyId) throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
        ReturnMessage<Department> returnMessage = departmentService.queryDepartmentByCompanyIdAndRelationType(companyId);
        if("999".equals(returnMessage.getCode())){
            rootMap.put("code", "999");
            rootMap.put("mesg", "查询错误");
            return rootMap;
        }
        rootMap.put("code", returnMessage.getCode());
        rootMap.put("mesg", returnMessage.getMesg());
        rootMap.put("data", returnMessage.getList());
        return rootMap;
    }

}
