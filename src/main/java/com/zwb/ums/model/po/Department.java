package com.zwb.ums.model.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: Department
 * </p>
 * <p>
 * Description: 部门实体类
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月3日 下午5:58:53
 * @version 1.0
 */
public class Department implements Serializable {
    /** 序列化 */
	private static final long serialVersionUID = 1L;

	/** 主键ID */
    private String departmentId;

    /** 部门编码 */
    private String departmentNum;

    /** 部门名称 */
    private String departmentName;

    /** 部门状态（00无效 01有效） */
    private String departmentState;

    /** 行政区域编码 */
    private String distractId;

    /** 区域编码 */
    private String areaId;

    /** 排序号 */
    private Integer sortNum;

    /** 创建时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /*** 关联Id */
    private String relationId;

    /** 所属公司  */
    private Company company;

    /** 部门备注 */
    private String departmentRemark;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(String departmentNum) {
        this.departmentNum = departmentNum == null ? null : departmentNum.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getDepartmentState() {
        return departmentState;
    }

    public void setDepartmentState(String departmentState) {
        this.departmentState = departmentState == null ? null : departmentState.trim();
    }

    public String getDistractId() {
        return distractId;
    }

    public void setDistractId(String distractId) {
        this.distractId = distractId == null ? null : distractId.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDepartmentRemark() {
        return departmentRemark;
    }

    public void setDepartmentRemark(String departmentRemark) {
        this.departmentRemark = departmentRemark == null ? null : departmentRemark.trim();
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}