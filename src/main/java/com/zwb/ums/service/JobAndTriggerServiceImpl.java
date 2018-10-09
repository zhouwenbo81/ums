package com.zwb.ums.service;

import com.entcms.sdk.ReturnMessage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwb.ums.mapper.JobAndTriggerMapper;
import com.zwb.ums.model.po.JobAndTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Title: JobAndTriggerServiceImpl
 * </p>
 * <p>
 * Description: 任务与触发器Service接口实现类
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/30 14:18
 */
@Service
public class JobAndTriggerServiceImpl implements JobAndTriggerService{

	@Autowired
	private JobAndTriggerMapper jobAndTriggerMapper;
	
	public ReturnMessage<JobAndTrigger> getJobAndTriggerDetail(int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
		List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();

		return new ReturnMessage<>("100","查询成功",list);
	}

	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
		PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
		return page;
	}

}