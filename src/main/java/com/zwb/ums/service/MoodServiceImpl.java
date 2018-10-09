package com.zwb.ums.service;

import java.util.ArrayList;
import java.util.List;

import com.zwb.ums.mapper.MoodMapper;
import com.zwb.ums.model.po.Mood;
import com.zwb.ums.model.vo.MoodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entcms.sdk.BaseServiceImpl;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.SplitPage;
import com.entcms.sdk.exception.ENTCMSException;


/**
 * <p>
 * Title: MoodServiceImpl
 * </p>
 * <p>
 * Description:	心情Service实现类
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * @author zhouwenbo
 * @date 2017年10月26日 下午6:26:36
 * @version 1.0
 */
@Service(value="moodService")
public class MoodServiceImpl extends BaseServiceImpl<Mood> implements MoodService {

	@Autowired
	private MoodMapper moodMapper;

	/**
	 * @Title: fdeleteMoodByIds
	 * @Description: 批量删除心情记录
	 * @param ids	待删除的Id数组
	 * @return
	 * @throws ENTCMSException
	 * @author zhouwenbo
	 * @date 2017年10月27日 下午2:08:14
	 * @version 1.0
	 */
	@Override
	public ReturnMessage<Mood> fdeleteMoodByIds(String[] ids)throws ENTCMSException {
		int num =0;
		Mood mood = new Mood();
		mood.setMoodState("10");
		for(String id: ids){
			mood.setMoodId(id);
			int i = moodMapper.updateByPrimaryKeySelective(mood);
			if(i==1){
				num++;
			}
		}
		return new ReturnMessage<>("100", "删除成功。", num);
	}

	/**
	 * @Title: queryMoodList
	 * @Description: 分页查询心情列表
	 * @param moodVO	自定义查询对象
	 * @return
	 * @throws ENTCMSException
	 * @author zhouwenbo
	 * @date 2017年10月27日 下午2:01:50
	 * @version 1.0
	 */
	@Override
	public ReturnMessage<Mood> queryMoodList(MoodVO moodVO)throws ENTCMSException{
		int rowCount = moodMapper.selectAllObjCount(moodVO);
		List<Mood> list = moodMapper.selectAllObj(moodVO);
		SplitPage splitPage = moodVO.getSplitPage();
		if(list.size()==0 && splitPage.getPageNum()>1){
			splitPage.setPageNum(splitPage.getPageNum()-1);
			list = moodMapper.selectAllObj(moodVO);
		}
		splitPage.setRowCount(rowCount);
		moodVO.setSplitPage(splitPage);
		return new ReturnMessage<>("100", "查询成功。", list, splitPage);
	}

	/**
	 * @Title: queryAllMoodGrByYearMonth
	 * @Description: 按照年月日排序查询心情记录信息
	 * @return
	 * @throws ENTCMSException
	 * @author zhouwenbo
	 * @date 2017年10月27日 下午3:27:35
	 * @version 1.0
	 */
	@Override
	public ReturnMessage<Mood> queryAllMoodGrByYearMonth()throws ENTCMSException{
		List<Mood> newList = new ArrayList<Mood>();
		List<Mood> monthsList = new ArrayList<Mood>();
		List<Mood> list = moodMapper.selectAllYearObj();
		for(Mood mood: list){
			List<Mood> monthList = moodMapper.selectAllMonthObj(mood.getMoodYear());
			for(Mood mood2: monthList){
				List<Mood> onedayList = moodMapper.selectAllMoodList(mood.getMoodYear(), mood2.getMoodMonth());
				mood2.setOnedayList(onedayList);
				monthsList.add(mood2);
			}
			mood.setMonthList(monthsList);
			newList.add(mood);
		}
		return new ReturnMessage<>("100", "查询成功", newList);
	}

	/**
	 * <p>Title: fdeleteObjectById</p>
	 * <p>Description: 根据Id删除心情信息</p>
	 * @param moodId
	 * @return
	 * @see MoodService#fdeleteObjectById(String)
	 */
	@Override
	public ReturnMessage<Mood> fdeleteObjectById(String moodId)throws ENTCMSException {
		Mood mood = new Mood();
		mood.setMoodId(moodId);
		mood.setMoodState("10");
		int i = moodMapper.updateByPrimaryKeySelective(mood);
		if(i!=1){
			return new ReturnMessage<>("999", "删除失败");
		}
		return new ReturnMessage<>("100", "删除成功", i);
	}

}
