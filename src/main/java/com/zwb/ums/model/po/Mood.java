package com.zwb.ums.model.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Title: Mood
 * </p>
 * <p>
 * Description: 心情实体对象
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * @author zhouwenbo
 * @date 2017年10月26日 下午5:20:04
 * @version 1.0
 */
public class Mood implements Serializable{
	
	/** 序列号  */
	private static final long serialVersionUID = 1L;

	/** 主键  */
	private String moodId;

	/** 心情标题  */
    private String moodTitle;

    /** 心情内容  */
    private String moodContent;

    /** 年  */
    private Integer moodYear;

    /** 月  */
    private Integer moodMonth;

    /** 日  */
    private Integer moodDay;

    /** 心情状态(00无效，01有效，10删除) */
    private String moodState;

    /** 序列号  */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    /** 月份列表  */
    private List<Mood> monthList;
    
    /** 具体日期心情列表  */
    private List<Mood> onedayList;
    
    public String getMoodId() {
        return moodId;
    }

    public void setMoodId(String moodId) {
        this.moodId = moodId == null ? null : moodId.trim();
    }

    public String getMoodTitle() {
        return moodTitle;
    }

    public void setMoodTitle(String moodTitle) {
        this.moodTitle = moodTitle == null ? null : moodTitle.trim();
    }

    public String getMoodContent() {
        return moodContent;
    }

    public void setMoodContent(String moodContent) {
        this.moodContent = moodContent == null ? null : moodContent.trim();
    }

    public Integer getMoodYear() {
        return moodYear;
    }

    public void setMoodYear(Integer moodYear) {
        this.moodYear = moodYear;
    }

    public Integer getMoodMonth() {
        return moodMonth;
    }

    public void setMoodMonth(Integer moodMonth) {
        this.moodMonth = moodMonth;
    }

    public Integer getMoodDay() {
        return moodDay;
    }

    public void setMoodDay(Integer moodDay) {
        this.moodDay = moodDay;
    }

    public String getMoodState() {
        return moodState;
    }

    public void setMoodState(String moodState) {
        this.moodState = moodState == null ? null : moodState.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public List<Mood> getMonthList() {
		return monthList;
	}

	public List<Mood> getOnedayList() {
		return onedayList;
	}

	public void setMonthList(List<Mood> monthList) {
		this.monthList = monthList;
	}

	public void setOnedayList(List<Mood> onedayList) {
		this.onedayList = onedayList;
	}
    
    
    
}