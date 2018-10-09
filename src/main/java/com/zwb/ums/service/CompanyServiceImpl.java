package com.zwb.ums.service;

import java.util.List;

import com.zwb.ums.mapper.CompanyMapper;
import com.zwb.ums.model.po.Company;
import com.zwb.ums.model.vo.CompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entcms.sdk.BaseServiceImpl;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.SplitPage;
import com.entcms.sdk.exception.ENTCMSException;


/**
 * <p>
 * Title: CompanyServiceImpl
 * </p>
 * <p>
 * Description: TODO
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月4日 下午1:48:54
 * @version 1.0
 */
@Service("companyService")
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	
	/**
	 * <p>Title: fdeleteCompanyByIds</p>
	 * <p>Description: </p>
	 * @param ids
	 * @return
	 * @throws ENTCMSException
	 * @see com.zwb.ums.service.CompanyService#fdeleteCompanyByIds(String[])
	 */
	@Override
	public ReturnMessage<Company> fdeleteCompanyByIds(String[] ids) throws ENTCMSException {
		int count = 0;
		Company company = new Company();
		company.setCompanyState("10");
		for(String id: ids){
			company.setCompanyId(id);
			int i = companyMapper.updateByPrimaryKeySelective(company);
			if(i==1){
				count++;
			}
		}
		return new ReturnMessage<>("100", "删除成功",count);
	}

	/**
	 * <p>Title: queryCompanyList</p>
	 * <p>Description: </p>
	 * @param companyVO
	 * @return
	 * @throws ENTCMSException
	 * @see com.zwb.ums.service.CompanyService#queryCompanyList(com.zwb.ums.model.vo.CompanyVO)
	 */
	@Override
	public ReturnMessage<Company> queryCompanyList(CompanyVO companyVO) throws ENTCMSException {
		int rowCount = companyMapper.selectAllObjCount(companyVO);
		List<Company> list = companyMapper.selectAllObj(companyVO);
		//删除最后一页后返回上一页
		SplitPage splitPage = companyVO.getSplitPage();
		if(list.size() == 0 && splitPage.getPageNum() > 1){
			splitPage.setPageNum(splitPage.getPageNum() - 1);
			list = companyMapper.selectAllObj(companyVO);
		}
		splitPage.setRowCount(rowCount);
		companyVO.setSplitPage(splitPage);
		return new ReturnMessage<Company>("100", "查询成功！", list, splitPage);
	}

	/**
	 * <p>Title: fdeleteCompanyById</p>
	 * <p>Description: </p>
	 * @param companyId
	 * @return
	 * @throws ENTCMSException
	 * @see com.zwb.ums.service.CompanyService#fdeleteCompanyById(String)
	 */
	@Override
	public ReturnMessage<Company> fdeleteCompanyById(String companyId) throws ENTCMSException {
		Company company = new Company();
		company.setCompanyState("10");
		company.setCompanyId(companyId);
		int i = companyMapper.updateByPrimaryKeySelective(company);
		if(i==1){
			return new ReturnMessage<>("100", "删除成功");
		}
		return new ReturnMessage<>("999", "删除失败");
	}

	@Override
	public ReturnMessage<Company> queryAllCompany(Company company) throws ENTCMSException {
		List<Company> list = companyMapper.selectAllCompany(company);
		return new ReturnMessage<>("100","查询成功",list);
	}

}
