package com.zwb.ums.model.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: Employment
 * </p>
 * <p>
 * Description: 职位实体类
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月3日 下午5:58:29
 * @version 1.0
 */
public class Employment implements Serializable {
    /** 序列化 */
	private static final long serialVersionUID = 1L;

	/** 主键 */
    private String employmentId;

    /** 职位名称 */
    private String employmentName;

    /** 排序号 */
    private Integer sortNum;

    /** 状态（00无效 01有效） */
    private String employmentState;

    /** 创建时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(String employmentId) {
        this.employmentId = employmentId == null ? null : employmentId.trim();
    }

    public String getEmploymentName() {
        return employmentName;
    }

    public void setEmploymentName(String employmentName) {
        this.employmentName = employmentName == null ? null : employmentName.trim();
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getEmploymentState() {
        return employmentState;
    }

    public void setEmploymentState(String employmentState) {
        this.employmentState = employmentState == null ? null : employmentState.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}