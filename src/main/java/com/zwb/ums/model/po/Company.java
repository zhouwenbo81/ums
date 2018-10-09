package com.zwb.ums.model.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: Company
 * </p>
 * <p>
 * Description: 公司实体类
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月3日 下午5:49:59
 * @version 1.0
 */
public class Company implements Serializable {
    /** 序列化 */
	private static final long serialVersionUID = 1L;

	/** 主键 */
    private String companyId;

    /** 公司名称 */
    private String companyName;

    /** 公司简称 */
    private String companyAbbreviation;

    /** 办公地址 */
    private String officeAddress;

    /** 公司介绍 */
    private String companyDescription;

    /** 公司状态（00无效 01有效） */
    private String companyState;

    /** 创建时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAbbreviation() {
        return companyAbbreviation;
    }

    public void setCompanyAbbreviation(String companyAbbreviation) {
        this.companyAbbreviation = companyAbbreviation == null ? null : companyAbbreviation.trim();
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress == null ? null : officeAddress.trim();
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription == null ? null : companyDescription.trim();
    }

    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState == null ? null : companyState.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}