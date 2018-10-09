package com.zwb.ums.mapper;


import com.entcms.sdk.BaseMapper;
import com.zwb.ums.model.po.Mood;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * <p>
 * Title: MoodMapper
 * </p>
 * <p>
 * Description: 心情Mapper接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * @author zhouwenbo
 * @date 2017年10月26日 下午5:18:34
 * @version 1.0
 */
//@Mapper
public interface MoodMapper extends BaseMapper<Mood>{
	
	/**
	 * @Title: selectAllYearObj
	 * @Description: 查询心情按照年份的分组
	 * @return   
	 * @author zhouwenbo
	 * @date 2017年10月26日 下午6:00:46
	 * @version 1.0
	 */
	public List<Mood> selectAllYearObj();
	
	/**
	 * @Title: selectAllMonthObj
	 * @Description: 查询指定年份下的月份分组
	 * @param moodYear 年份
	 * @return   
	 * @author zhouwenbo
	 * @date 2017年10月26日 下午6:01:03
	 * @version 1.0
	 */
	public List<Mood> selectAllMonthObj(Integer moodYear);
	
	/**
	 * @Title: selectAllMoodList
	 * @Description: 查询心情列表
	 * @param moodYear	年份
	 * @param moodMonth	月份
	 * @return   
	 * @author zhouwenbo
	 * @date 2017年10月26日 下午6:17:36
	 * @version 1.0
	 */
	public List<Mood> selectAllMoodList(Integer moodYear, Integer moodMonth);
    
}