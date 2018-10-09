package com.zwb.ums.model.po;

import java.io.Serializable;

/**
 * 
 * <p>
 * Title: Relation
 * </p>
 * <p>
 * Description: 公司、部门、职位、用户关系实体
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author Stone
 * @date 2017年6月7日 下午4:07:11
 * @version 1.0
 */
public class Relation implements Serializable {
    /** 序列化 */
	private static final long serialVersionUID = 1L;

	/** 主键 */
    private String relationId;

    /** 公司Id */
    private String companyId;

    /** 部门Id */
    private String departmentId;

    /** 职位Id */
    private String employmentId;

    /** 用户Id */
    private String userId;

    /** 关系类型（00公司部门 01公司部门职位 02公司部门用户 03公司部门职位用户） */
    private String relationType;

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(String employmentId) {
        this.employmentId = employmentId == null ? null : employmentId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType == null ? null : relationType.trim();
    }
}