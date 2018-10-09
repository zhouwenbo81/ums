package com.zwb.ums.model.vo;

import com.entcms.sdk.SplitPage;
import com.zwb.ums.model.po.Umsuser;

/**
 * <p>
 * Title: UmsuserVO
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/9 14:48
 */
public class UmsuserVO extends Umsuser {

    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /** 关键词 */
    public String keyWords;

    /** 分页信息 */
    private SplitPage splitPage;

    public SplitPage getSplitPage() {
        return splitPage;
    }

    public void setSplitPage(SplitPage splitPage) {
        this.splitPage = splitPage;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
