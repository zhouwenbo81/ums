package com.zwb.ums.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zwb.ums.model.po.Mood;
import com.zwb.ums.model.vo.MoodVO;
import com.zwb.ums.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entcms.sdk.HandleUtil;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.SplitPage;
import com.entcms.sdk.exception.ENTCMSException;


/**
 *
 * <p>
 * Title: ApiMoodController
 * </p>
 * <p>
 * Description: 测试心情Controller
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @date 2018年4月27日 下午3:44:19
 * @version 1.0
 */
@Controller
public class ApiMoodController{

	@Autowired
	private MoodService moodService;

	/**
	 * @Title: addMood
	 * @Description: 添加数据
	 * @param mood
	 * @return
	 * @author zhouwenbo
	 * @date 2018年4月27日 下午4:02:36
	 * @version 1.0
	 */
	@RequestMapping("/api/mood/add")
	@ResponseBody
	public Map<String, Object> addMood(@RequestBody Mood mood) {
		Map<String, Object> rootMap = new HashMap<String, Object>();
		try {
			//后台校验
			if(StringUtils.isEmpty(mood.getMoodTitle())){
				rootMap.put("code", "999");
				rootMap.put("mesg", "名称不能为空");
				return rootMap;
			}
			Date date = new Date();
			SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat monthSdf = new SimpleDateFormat("MM");
			SimpleDateFormat daySdf = new SimpleDateFormat("dd");
			mood.setMoodId(HandleUtil.UUID32());
			mood.setCreateTime(date);
			mood.setMoodYear(Integer.valueOf(yearSdf.format(date)));
			mood.setMoodMonth(Integer.valueOf(monthSdf.format(date)));
			mood.setMoodDay(Integer.valueOf(daySdf.format(date)));
			ReturnMessage<Mood> returnMessage = moodService.addObject(mood);
			if("999".equals(returnMessage.getCode())){
				rootMap.put("code", "999");
				rootMap.put("mesg", "添加数据失败！");
				return rootMap;
			}
			rootMap.put("code", returnMessage.getCode());
			rootMap.put("mesg", returnMessage.getMesg());
			return rootMap;
		} catch (Exception e) {
			e.printStackTrace();
			rootMap.put("code", "999");
			rootMap.put("mesg", e.getMessage());
			return rootMap;
		}
	}

	/**
	 * @Title: deleteObject
	 * @Description: TODO
	 * @param id
	 * @return
	 * @author zhouwenbo
	 * @date 2018年4月27日 下午7:21:21
	 * @version 1.0
	 */
	@RequestMapping("/api/mood/delete/{id}")
	@ResponseBody
	public Map<String, Object> deleteMood(@PathVariable("id") String id){
		Map<String, Object> rootMap = new HashMap<String,Object>();
		try {
			ReturnMessage<Mood> returnMessage = moodService.deleteObjectById(id);
			if("999".equals(returnMessage.getCode())){
				rootMap.put("code", "999");
				rootMap.put("mesg", "删除失败");
				return rootMap;
			}
			rootMap.put("code", "100");
			rootMap.put("mesg", "删除成功");
			return rootMap;
		} catch (Exception e) {
			e.printStackTrace();
			rootMap.put("code", "999");
			rootMap.put("mesg", e.getMessage());
			return rootMap;
		}
	}

	@RequestMapping("/api/mood/fdelete/{id}")
	@ResponseBody
	public Map<String, Object> fdeleteMood(@PathVariable("id") String id){
		Map<String, Object> rootMap = new HashMap<String,Object>();
		try {
			ReturnMessage<Mood> returnMessage = moodService.fdeleteObjectById(id);
			if("999".equals(returnMessage.getCode())){
				rootMap.put("code", "999");
				rootMap.put("mesg", "删除失败");
				return rootMap;
			}
			rootMap.put("code", "100");
			rootMap.put("mesg", "删除成功");
			return rootMap;
		} catch (Exception e) {
			e.printStackTrace();
			rootMap.put("code", "999");
			rootMap.put("mesg", e.getMessage());
			return rootMap;
		}
	}

	/**
	 * @Title: fdeleteMoodByIds
	 * @Description: 批量软删除
	 * @param ids 待删除的Id数组
	 * @return
	 * @throws ENTCMSException
	 * @author zhouwenbo
	 * @date 2018年4月28日 下午3:30:01
	 * @version 1.0
	 */
	@RequestMapping("/api/mood/fdelete/all")
	@ResponseBody
	public Map<String, Object> fdeleteMoodByIds(@RequestBody String[] ids) throws ENTCMSException{
		Map<String, Object> rootMap = new HashMap<String,Object>();
		ReturnMessage<Mood> returnMessage = moodService.fdeleteMoodByIds(ids);
		if("100".equals(returnMessage.getCode())){
			rootMap.put("code", "100");
			rootMap.put("mesg", "删除成功");
			return rootMap;
		}
		rootMap.put("code", "999");
		rootMap.put("mesg", "删除失败");
		return rootMap;
	}

	/**
	 * @Title: modifyMood
	 * @Description: 修改
	 * @param mood
	 * @return
	 * @throws ENTCMSException
	 * @author zhouwenbo
	 * @date 2018年4月28日 下午3:39:31
	 * @version 1.0
	 */
	@RequestMapping("/api/mood/modify")
	@ResponseBody
	public Map<String, Object> modifyMood(@RequestBody Mood mood)throws ENTCMSException{
		Map<String, Object> rootMap = new HashMap<String,Object>();
		ReturnMessage<Mood> returnMessage = moodService.updateObjectByPrimaryKeySelective(mood);
		if("999".equals(returnMessage.getCode())){
			rootMap.put("code", "999");
			rootMap.put("mesg", "修改失败！");
			return rootMap;
		}
		rootMap.put("code", returnMessage.getCode());
		rootMap.put("mesg", returnMessage.getMesg());
		return rootMap;
	}

	/**
	 * @Title: queryMoodById
	 * @Description: 根据Id查询mood信息
	 * @param id
	 * @return
	 * @throws ENTCMSException
	 * @author zhouwenbo
	 * @date 2018年4月28日 下午3:54:08
	 * @version 1.0
	 */
	@RequestMapping("/api/mood/queryone/{id}")
	@ResponseBody
	public Map<String, Object> queryMoodById(@PathVariable("id") String id) throws ENTCMSException{
		Map<String, Object> rootMap = new HashMap<String,Object>();
		ReturnMessage<Mood> returnMessage = moodService.queryObjectById(id);
		if("999".equals(returnMessage.getCode())){
			rootMap.put("code", "999");
			rootMap.put("mesg", "查询错误");
			return rootMap;
		}
		rootMap.put("code", returnMessage.getCode());
		rootMap.put("mesg", returnMessage.getMesg());
		rootMap.put("data", returnMessage.getObject());
		return rootMap;
	}

	/**
	 * @Title: queryMoodListAndPage
	 * @Description: 分页查询列表数据
	 * @return
	 * @author zhouwenbo
	 * @date 2018年4月28日 下午3:58:36
	 * @version 1.0
	 * @throws ENTCMSException
	 */
	@RequestMapping("/api/mood/query/list/{pageNum}")
	@ResponseBody
	public Map<String, Object> queryMoodListAndPage(MoodVO moodVO, @PathVariable("pageNum")int pageNum,
                                                    @RequestParam(value="pageSize",defaultValue="10")int pageSize) throws ENTCMSException {
		Map<String, Object> rootMap = new HashMap<String,Object>();
		SplitPage splitPage = new SplitPage();
		splitPage.setPageNum(pageNum);
		splitPage.setPageSize(pageSize);
		moodVO.setSplitPage(splitPage);
		ReturnMessage<Mood> returnMessage = moodService.queryMoodList(moodVO);
		if("999".equals(returnMessage.getCode())){
			rootMap.put("code", "999");
			rootMap.put("mesg", "查询失败！");
			return rootMap;
		}
		rootMap.put("code", returnMessage.getCode());
		rootMap.put("mesg", returnMessage.getMesg());
		rootMap.put("data", returnMessage.getList() !=null ? returnMessage.getList() : "");
		rootMap.put("pageNum", returnMessage.getSplitPage() !=null ? returnMessage.getSplitPage().getPageNum() : "");
		rootMap.put("pageSize", returnMessage.getSplitPage() !=null ? returnMessage.getSplitPage().getPageSize() : "");
		rootMap.put("pageCount", returnMessage.getSplitPage() !=null ? returnMessage.getSplitPage().getRowCount() : "");
		return rootMap;
	}

}
