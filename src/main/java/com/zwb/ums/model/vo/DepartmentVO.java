package com.zwb.ums.model.vo;

import com.entcms.sdk.SplitPage;
import com.zwb.ums.model.po.Company;
import com.zwb.ums.model.po.Department;

/**
 * <p>
 * Title: DepartmentVO
 * </p>
 * <p>
 * Description: TODO
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月3日 下午6:07:36
 * @version 1.0
 */
public class DepartmentVO extends Department {

	/** 序列号  */
	private static final long serialVersionUID = 1L;

	/*** 所属公司Id */
	private String companyId;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/** 分页信息 */
	private SplitPage splitPage;

	public SplitPage getSplitPage() {
		return splitPage;
	}

	public void setSplitPage(SplitPage splitPage) {
		this.splitPage = splitPage;
	}

}
