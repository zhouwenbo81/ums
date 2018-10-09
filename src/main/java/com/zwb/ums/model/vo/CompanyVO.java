package com.zwb.ums.model.vo;

import com.entcms.sdk.SplitPage;
import com.zwb.ums.model.po.Company;

/**
 * <p>
 * Title: CompanyVO
 * </p>
 * <p>
 * Description: TODO
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月3日 下午6:06:38
 * @version 1.0
 */
public class CompanyVO extends Company {

	/** 序列号  */
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
