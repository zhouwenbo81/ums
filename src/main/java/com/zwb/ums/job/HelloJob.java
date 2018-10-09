package com.zwb.ums.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * <p>
 * Title: HelloJob
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
 * @date 2018/5/30 15:02
 */
public class HelloJob implements BaseJob {

    private static Logger logger = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob(){

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Hello Job执行时间："+new Date());
        //填充HelloJob的业务逻辑
    }
}
