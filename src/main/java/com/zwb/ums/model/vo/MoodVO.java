package com.zwb.ums.model.vo;

import com.entcms.sdk.SplitPage;
import com.zwb.ums.model.po.Mood;

/**
 *
 */
public class MoodVO extends Mood {

	/** 序列号 */
	private static final long serialVersionUID = 1L;
	
	/** 分页信息 */
	private SplitPage splitPage;

	public SplitPage getSplitPage() {
		return splitPage;
	}

	public void setSplitPage(SplitPage splitPage) {
		this.splitPage = splitPage;
	}

}
