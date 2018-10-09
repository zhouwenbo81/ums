package com.zwb.ums.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * <p>
 * Title: DynamicTask
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
 * @date 2018/8/13 15:21
 */
@RestController
@Component
public class DynamicTask {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;

    private static Logger logger = LoggerFactory.getLogger(DynamicTask.class);

    @Bean
    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @RequestMapping("/startCron")
    public String startCron() {
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("0/5 * * * * *"));
        System.out.println("DynamicTask.startCron();");
        logger.info("开始执行任务cron5 ------>"+new Date());
        return "startCron";
    }

    @RequestMapping("/stopCron")
    public String stopCron(){
        if(future != null){
            future.cancel(true);
        }
        logger.info("暂停任务cron5----->"+new Date());
        return "stopCron";
    }

    @RequestMapping("/changeCron10")
    public String startCron10() {
        stopCron(); //先暂停任务，再开启任务
        future = threadPoolTaskScheduler.schedule(new MyRunnable(),new CronTrigger("0/10 * * * * *"));
        logger.info("DynamicTask.startCron10()  -------> " + new Date());
        return "changeCron10";
    }




    private class MyRunnable implements Runnable {
        @Override
        public void run() {
//            System.out.print("DynamicTask.MyRunnable.run()------"+new Date());
            logger.info("DynamicTask.MyRunnable.run()------"+ new Date());
        }
    }

}
