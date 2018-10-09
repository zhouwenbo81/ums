package com.zwb.ums.service;

import com.entcms.sdk.BaseService;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.model.po.Mood;
import com.zwb.ums.model.vo.MoodVO;


/**
 * <p>
 * Title: MoodService
 * </p>
 * <p>
 * Description: 心情Service
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * @author zhouwenbo
 * @date 2017年10月26日 下午6:24:29
 * @version 1.0
 */
public interface MoodService extends BaseService<Mood> {

	/**
	 * @Title: fdeleteMoodByIds
	 * @Description: 批量删除心情信息（软删除）
	 * @param ids	待删除的心情Id数组
	 * @return
	 * @throws ENTCMSException
	 * @author zhouwenbo
	 * @date 2017年10月27日 下午4:05:31
	 * @version 1.0
	 */
	public ReturnMessage<Mood> fdeleteMoodByIds(String[] ids) throws ENTCMSException;

	/**
	 * @Title: queryMoodList
	 * @Description: 分页查询心情信息列表
	 * @param moodVO
	 * @return
	 * @throws ENTCMSException
	 * @author zhouwenbo
	 * @date 2017年10月27日 下午4:05:49
	 * @version 1.0
	 */
	public ReturnMessage<Mood> queryMoodList(MoodVO moodVO) throws ENTCMSException;

	/**
	 * @Title: queryAllMoodGrByYearMonth
	 * @Description: 按照年月日格式查询心情信息
	 * @return
	 * @throws ENTCMSException
	 * @author zhouwenbo
	 * @date 2017年10月27日 下午4:05:55
	 * @version 1.0
	 */
	public ReturnMessage<Mood> queryAllMoodGrByYearMonth() throws ENTCMSException;

	/**
	 * @Title: fdeleteObjectById
	 * @Description: 根据Id软删除
	 * @param moodId
	 * @return   
	 * @author zhouwenbo
	 * @date 2018年5月2日 下午5:47:24
	 * @version 1.0
	 */
	public ReturnMessage<Mood> fdeleteObjectById(String moodId)throws ENTCMSException;
	
	

}
