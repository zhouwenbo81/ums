package com.zwb.ums.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>
 * Title: BaseJob
 * </p>
 * <p>
 * Description: 基础的任务类
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/30 14:03
 */
public interface BaseJob extends Job {

    public void execute(JobExecutionContext context) throws JobExecutionException;
}
