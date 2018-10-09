package com.zwb.ums.service;

import com.entcms.sdk.BaseService;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.model.po.Company;
import com.zwb.ums.model.vo.CompanyVO;

/**
 * <p>
 * Title: CompanyService
 * </p>
 * <p>
 * Description: 公司Service接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月4日 下午1:30:52
 * @version 1.0
 */
public interface CompanyService extends BaseService<Company> {
	
	/**
	 * @Title: fdeleteCompanyByIds
	 * @Description: 批量软删除公司信息
	 * @param ids
	 * @return
	 * @throws ENTCMSException   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午1:34:29
	 * @version 1.0
	 */
	public ReturnMessage<Company> fdeleteCompanyByIds(String[] ids)throws ENTCMSException;
	
	/**
	 * @Title: queryCompanyList
	 * @Description: 查询公司列表
	 * @param companyVO
	 * @return
	 * @throws ENTCMSException   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午1:36:07
	 * @version 1.0
	 */
	public ReturnMessage<Company> queryCompanyList(CompanyVO companyVO) throws ENTCMSException;
	
	/**
	 * @Title: fdeleteCompanyById
	 * @Description: 根据Id删除公司信息
	 * @param companyId 公司Id
	 * @return
	 * @throws ENTCMSException   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午1:37:30
	 * @version 1.0
	 */
	public ReturnMessage<Company> fdeleteCompanyById(String companyId) throws ENTCMSException;

	/**
	 * @Title: queryAllCompany
	 * @Description: 查询全部公司
	 * @param: company
	 * @return: 
	 * @author zhouwenbo
	 * @date 2018/5/7 17:14
	 * @version 1.0
	 */
	public ReturnMessage<Company> queryAllCompany(Company company) throws ENTCMSException;

}
