package com.zwb.ums.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zwb.ums.model.po.Company;
import com.zwb.ums.model.vo.CompanyVO;
import com.zwb.ums.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entcms.sdk.HandleUtil;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.SplitPage;
import com.entcms.sdk.exception.ENTCMSException;

/**
 * <p>
 * Title: ApiCompanyController
 * </p>
 * <p>
 * Description: TODO
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月4日 下午2:03:02
 * @version 1.0
 */
@Controller
public class ApiCompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * @Title: addCompany
	 * @Description: 添加公司信息
	 * @param company
	 * @return   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午2:09:28
	 * @version 1.0
	 */
	@RequestMapping("/api/company/add")
	@ResponseBody
	public Map<String, Object> addCompany(@RequestBody Company company) {
		Map<String, Object> rootMap = new HashMap<String, Object>();
		try {
			//后台校验
			if(StringUtils.isEmpty(company.getCompanyName())){
				rootMap.put("code", "999");
				rootMap.put("mesg", "公司名称不能为空");
				return rootMap;
			}
			company.setCompanyId(HandleUtil.UUID32());
			company.setCreateTime(new Date());
			
			ReturnMessage<Company> returnMessage = companyService.addObject(company);
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
	 * @Title: deleteCompany
	 * @Description: TODO
	 * @param id
	 * @return   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午2:22:40
	 * @version 1.0
	 */
	@RequestMapping("/api/company/delete/{id}")
	@ResponseBody
	public Map<String, Object> deleteCompany(@PathVariable("id") String id){
		Map<String, Object> rootMap = new HashMap<String,Object>();
		try {
			ReturnMessage<Company> returnMessage = companyService.deleteObjectById(id);
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
	 * @Title: fdeleteCompany
	 * @Description: TODO
	 * @param id
	 * @return   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午2:22:45
	 * @version 1.0
	 */
	@RequestMapping("/api/company/fdelete/{id}")
	@ResponseBody
	public Map<String, Object> fdeleteCompany(@PathVariable("id") String id){
		Map<String, Object> rootMap = new HashMap<String,Object>();
		try {
			ReturnMessage<Company> returnMessage = companyService.fdeleteCompanyById(id);
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
	 * @Title: fdeleteCompanyByIds
	 * @Description: TODO
	 * @param ids
	 * @return
	 * @throws ENTCMSException   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午2:22:52
	 * @version 1.0
	 */
	@RequestMapping("/api/company/fdelete/all")
	@ResponseBody
	public Map<String, Object> fdeleteCompanyByIds(@RequestBody String[] ids) throws ENTCMSException{
		Map<String, Object> rootMap = new HashMap<String,Object>();
		ReturnMessage<Company> returnMessage = companyService.fdeleteCompanyByIds(ids);
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
	 * @Title: modifyCompany
	 * @Description: TODO
	 * @param company
	 * @return
	 * @throws ENTCMSException   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午2:22:57
	 * @version 1.0
	 */
	@RequestMapping("/api/company/modify")
	@ResponseBody
	public Map<String, Object> modifyCompany(@RequestBody Company company)throws ENTCMSException{
		Map<String, Object> rootMap = new HashMap<String,Object>();
		ReturnMessage<Company> returnMessage = companyService.updateObjectByPrimaryKeySelective(company);
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
	 * @Title: queryCompanyById
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws ENTCMSException   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午2:23:04
	 * @version 1.0
	 */
	@RequestMapping("/api/company/queryone/{id}")
	@ResponseBody
	public Map<String, Object> queryCompanyById(@PathVariable("id") String id) throws ENTCMSException{
		Map<String, Object> rootMap = new HashMap<String,Object>();
		ReturnMessage<Company> returnMessage = companyService.queryObjectById(id);
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
	 * @Title: queryCompanyListAndPage
	 * @Description: TODO
	 * @param companyVO
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ENTCMSException   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午2:23:09
	 * @version 1.0
	 */
	@RequestMapping("/api/company/query/list/{pageNum}")
	@ResponseBody
	public Map<String, Object> queryCompanyListAndPage(@RequestBody CompanyVO companyVO, @PathVariable("pageNum")int pageNum,
													   @RequestParam(value="pageSize",defaultValue="10")int pageSize) throws ENTCMSException {
		Map<String, Object> rootMap = new HashMap<String,Object>();
		SplitPage splitPage = new SplitPage();
		splitPage.setPageNum(pageNum);
		splitPage.setPageSize(pageSize);
		companyVO.setSplitPage(splitPage);
		ReturnMessage<Company> returnMessage = companyService.queryCompanyList(companyVO);
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
	 * @Title: queryAllLoadCompany
	 * @Description: 获取需要加载的公司信息
	 * @param:
	 * @return:
	 * @author zhouwenbo
	 * @date 2018/5/7 17:20
	 * @version 1.0
	 */
	@RequestMapping("/api/company/load/all")
	@ResponseBody
	public Map<String, Object> queryAllLoadCompany() throws ENTCMSException{
		Map<String, Object> rootMap = new HashMap<String,Object>();
		Company company = new Company();
		ReturnMessage<Company> returnMessage = companyService.queryAllCompany(company);
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
