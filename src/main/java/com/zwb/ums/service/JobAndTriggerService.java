package com.zwb.ums.service;

import com.github.pagehelper.PageInfo;
import com.zwb.ums.model.po.JobAndTrigger;

/**
 * <p>
 * Title: JobAndTriggerService
 * </p>
 * <p>
 * Description: 任务与触发器Service接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/30 14:18
 */
public interface JobAndTriggerService {

    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
