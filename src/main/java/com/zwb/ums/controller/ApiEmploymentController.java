package com.zwb.ums.controller;

import com.entcms.sdk.HandleUtil;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.SplitPage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.model.po.Employment;
import com.zwb.ums.model.vo.EmploymentVO;
import com.zwb.ums.service.EmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: ApiEmploymentController
 * </p>
 * <p>
 * Description: 职位Controller
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/7 14:59
 */
@Controller
public class ApiEmploymentController {

    @Autowired
    private EmploymentService employmentService;

    @RequestMapping("/api/employment/add")
    @ResponseBody
    public Map<String, Object> addEmployment(@RequestBody EmploymentVO employmentVO) {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        Employment employment = employmentVO;
        String companyId = employmentVO.getCompanyId();
        String departmentId = employmentVO.getDepartmentId();
        ReturnMessage<Employment> returnMessage = null;
        try {
            //后台校验
            if(StringUtils.isEmpty(employment.getEmploymentName())){
                rootMap.put("code", "999");
                rootMap.put("mesg", "职位名称不能为空");
                return rootMap;
            }
            employment.setEmploymentId(HandleUtil.UUID32());
            employment.setCreateTime(new Date());
            if(!HandleUtil.isNull(companyId) && !HandleUtil.isNull(departmentId)){
                returnMessage = employmentService.addEmployment(employment, companyId, departmentId);
            }else {
                returnMessage = employmentService.addObject(employment);
            }
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

    @RequestMapping("/api/employment/query/list/{pageNum}")
    @ResponseBody
    public Map<String, Object> queryEmploymentListAndPage(@RequestBody EmploymentVO employmentVO, @PathVariable("pageNum")int pageNum,
                                                          @RequestParam(value="pageSize",defaultValue="10")int pageSize) throws ENTCMSException {
        Map<String, Object> rootMap = new HashMap<String,Object>();
        SplitPage splitPage = new SplitPage();
        splitPage.setPageNum(pageNum);
        splitPage.setPageSize(pageSize);
        employmentVO.setSplitPage(splitPage);
        ReturnMessage<EmploymentVO> returnMessage = employmentService.queryEmploymentList(employmentVO);
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

    @RequestMapping("/api/employment/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteEmployment(@PathVariable("id") String id){
        Map<String, Object> rootMap = new HashMap<String,Object>();
        try {
            ReturnMessage<Employment> returnMessage = employmentService.deleteObjectById(id);
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

    @RequestMapping("/api/employment/fdelete/{id}")
    @ResponseBody
    public Map<String, Object> fdeleteEmployment(@PathVariable("id") String id){
        Map<String, Object> rootMap = new HashMap<String,Object>();
        try {
            ReturnMessage<Employment> returnMessage = employmentService.fdeleteEmploymentById(id);
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

    @RequestMapping("/api/employment/fdelete/all")
    @ResponseBody
    public Map<String, Object> fdeleteEmploymentByIds(@RequestBody String[] ids) throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
        ReturnMessage<Employment> returnMessage = employmentService.fdeleteEmploymentByIds(ids);
        if("100".equals(returnMessage.getCode())){
            rootMap.put("code", "100");
            rootMap.put("mesg", "删除成功");
            return rootMap;
        }
        rootMap.put("code", "999");
        rootMap.put("mesg", "删除失败");
        return rootMap;
    }

    @RequestMapping("/api/employment/queryone/{id}")
    @ResponseBody
    public Map<String, Object> queryEmploymentById(@PathVariable("id") String id) throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
        ReturnMessage<EmploymentVO> returnMessage = employmentService.queryEmploymentById(id);
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

    @RequestMapping("/api/employment/modify/state")
    @ResponseBody
    public Map<String, Object> modifyEmploymentState(@RequestBody Employment employment)throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
        ReturnMessage<Employment> returnMessage = employmentService.updateObjectByPrimaryKeySelective(employment);
        if("999".equals(returnMessage.getCode())){
            rootMap.put("code", "999");
            rootMap.put("mesg", "修改失败！");
            return rootMap;
        }
        rootMap.put("code", returnMessage.getCode());
        rootMap.put("mesg", returnMessage.getMesg());
        return rootMap;
    }

    @RequestMapping("/api/employment/modify")
    @ResponseBody
    public Map<String, Object> modifyEmployment(@RequestBody EmploymentVO employment)throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
        ReturnMessage<Employment> returnMessage = employmentService.modifyEmployment(employment);
        if("999".equals(returnMessage.getCode())){
            rootMap.put("code", "999");
            rootMap.put("mesg", "修改失败！");
            return rootMap;
        }
        rootMap.put("code", returnMessage.getCode());
        rootMap.put("mesg", returnMessage.getMesg());
        return rootMap;
    }

}
