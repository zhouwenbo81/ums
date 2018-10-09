package com.zwb.ums.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Title: ScheduledService
 * </p>
 * <p>
 * Description: Spring task 任务类
 *          使用spring自带的spring task实现简单的定时任务
 *          使用spring boot整合quartz可以实现 更为复杂的任务
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/8/13 14:32
 */
@Component
public class ScheduledService {

    /***
     * 使用@Scheduled注解来设置任务的执行时间，并且使用三种属性配置方式：
     * 1.cron：通过表达式来配置任务执行时间
     * 2.fixedRate：定义一个按一定频率执行的定时任务
     * 3.fixedDelay 定义一个按一定频率执行的定时任务，与上面不同的是，改属性可以配合initialDelay， 定义该任务延迟执行时间。
     *
     * 在定时任务的类或者方法上添加@Async，使每一个任务都是在不同的线程中
     */

    private static Logger logger = LoggerFactory.getLogger(ScheduledService.class);

//    @Async
//    @Scheduled(cron = "0/5 * * * * *")
    public void scheduleJob(){
        logger.info("=======>>>>>> 使用cron :"+ System.currentTimeMillis());
    }

//    @Async
//    @Scheduled(fixedRate = 5000)
    public void scheduleJob2(){
        logger.info("=======>>>>>> 使用fixedRate :"+ System.currentTimeMillis());
    }

//    @Async
//    @Scheduled(fixedDelay = 5000)
    public void scheduleJob3(){
        logger.info("=======>>>>>> 使用fixedDelay :"+ System.currentTimeMillis());
    }


}
