package com.zwb.ums.controller;

import com.entcms.sdk.HandleUtil;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.SplitPage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.model.po.Umsuser;
import com.zwb.ums.model.vo.UmsuserVO;
import com.zwb.ums.service.UmsuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: ApiUmsUserController
 * </p>
 * <p>
 * Description: UMS用户Controller
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/11 9:29
 */
@Controller
public class ApiUmsUserController {
    
    @Autowired
    private UmsuserService umsuserService;

    /**
     * @Title: queryUmsUserListAndPage
     * @Description: 分页查询用户列表
     * @param: umsuserVO
     * @param: pageNum
     * @param: pageSize
     * @return:
     * @author zhouwenbo
     * @date 2018/5/11 9:37
     * @version 1.0
     */
    @RequestMapping("/api/umsuser/query/list/{pageNum}")
    @ResponseBody
    public Map<String, Object> queryUmsUserListAndPage(@RequestBody UmsuserVO umsuserVO, @PathVariable("pageNum")int pageNum,
                                                       @RequestParam(value="pageSize",defaultValue="10")int pageSize) throws ENTCMSException {
        Map<String, Object> rootMap = new HashMap<String,Object>();
        SplitPage splitPage = new SplitPage();
        splitPage.setPageNum(pageNum);
        splitPage.setPageSize(pageSize);
        umsuserVO.setSplitPage(splitPage);
        ReturnMessage<UmsuserVO> returnMessage = umsuserService.queryAllInsideUserList(umsuserVO);
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
     * @Title: addUmsUser
     * @Description: 添加用户
     * @param: umsuser
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/11 9:42
     * @version 1.0
     */
    @RequestMapping("/api/umsuser/add")
    @ResponseBody
    public Map<String, Object> addUmsUser(@RequestBody Umsuser umsuser) {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        try {
            //后台校验
            if(StringUtils.isEmpty(umsuser.getUsername())){
                rootMap.put("code", "999");
                rootMap.put("mesg", "用户名称不能为空");
                return rootMap;
            }
            umsuser.setId(HandleUtil.UUID32());
            umsuser.setCreateDate(new Date());
            ReturnMessage<Umsuser> returnMessage = umsuserService.addObject(umsuser);
            if("999".equals(returnMessage.getCode())){
                rootMap.put("code", "999");
                rootMap.put("mesg", "添加失败！");
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
     * @Title: deleteUmsUser
     * @Description: 删除用户信息
     * @param: 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/11 9:43
     * @version 1.0
     */
    @RequestMapping("/api/umsuser/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteUmsUser(@PathVariable("id") String id){
        Map<String, Object> rootMap = new HashMap<String,Object>();
        try {
            ReturnMessage<Umsuser> returnMessage = umsuserService.deleteObjectById(id);
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
     * @Title: fdeleteUmsUser
     * @Description: 根据id软删除用户信息
     * @param: id 用户Id
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/11 9:45
     * @version 1.0
     */
    @RequestMapping("/api/umsuser/fdelete/{id}")
    @ResponseBody
    public Map<String, Object> fdeleteUmsUser(@PathVariable("id") String id){
        Map<String, Object> rootMap = new HashMap<String,Object>();
        try {
            ReturnMessage<Umsuser> returnMessage = umsuserService.fdeleteUmsUserById(id);
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
     * @Title: fdeleteUmsUserByIds
     * @Description: 批量软删除用户信息
     * @param: ids
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/11 9:46
     * @version 1.0
     */
    @RequestMapping("/api/umsuser/fdelete/all")
    @ResponseBody
    public Map<String, Object> fdeleteUmsUserByIds(@RequestBody String[] ids) throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
        ReturnMessage<Umsuser> returnMessage = umsuserService.fdeleteUmsUserByIds(ids);
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
     * @Title: queryUmsUserById
     * @Description: 根据用户Id查询用户信息
     * @param: id
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/11 9:47
     * @version 1.0
     */
    @RequestMapping("/api/umsuser/queryone/{id}")
    @ResponseBody
    public Map<String, Object> queryUmsUserById(@PathVariable("id") String id) throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
        ReturnMessage<Umsuser> returnMessage = umsuserService.queryObjectById(id);
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
     * @Title: modifyUmsUser
     * @Description: 修改用户信息
     * @param: umsuser
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/11 9:49
     * @version 1.0
     */
    @RequestMapping("/api/umsuser/modify")
    @ResponseBody
    public Map<String, Object> modifyUmsUser(@RequestBody Umsuser umsuser)throws ENTCMSException{
        Map<String, Object> rootMap = new HashMap<String,Object>();
        ReturnMessage<Umsuser> returnMessage = umsuserService.updateObjectByPrimaryKeySelective(umsuser);
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
