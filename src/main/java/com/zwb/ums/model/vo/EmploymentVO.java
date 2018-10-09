package com.zwb.ums.model.vo;

import com.entcms.sdk.SplitPage;
import com.zwb.ums.model.po.Company;
import com.zwb.ums.model.po.Department;
import com.zwb.ums.model.po.Employment;

/**
 * <p>
 * Title: EmploymentVO
 * </p>
 * <p>
 * Description: TODO
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月3日 下午6:08:39
 * @version 1.0
 */
public class EmploymentVO extends Employment {

	/** 序列号  */
	private static final long serialVersionUID = 1L;

	/** 关联Id */
	private String relationId;

	/** 所属公司Id */
	private String companyId;

	/** 所属部门Id */
	private String departmentId;

	/** 所属公司 */
	private Company company;

	/** 所属部门 */
	private Department department;

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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
