package com.zwb.ums.mapper;

import com.zwb.ums.model.po.JobAndTrigger;

import java.util.List;

/**
 * <p>
 * Title: JobAndTriggerMapper
 * </p>
 * <p>
 * Description: 任务触发Mapper接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @date 2018年5月3日 下午5:47:56
 * @version 1.0
 */
public interface JobAndTriggerMapper {
	public List<JobAndTrigger> getJobAndTriggerDetails();
}
