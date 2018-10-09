package com.zwb.ums.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * <p>
 * Title: AsyncConfig
 * </p>
 * <p>
 * Description: 异步事件配置 ---- 多任务执行时配置异步事件，防止任务之间相互影响
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/8/13 14:15
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    /**
     *  @Configuration : 该类为一个配置类
     *  @EnableAsync : 开启异步事件的支持
     *
     */

    /** 核心线程数 */
    private int corePoolSize = 10;

    /** 最大线程数 */
    private int maxPoolSize = 200;

    /** 队列大小 */
    private int queueCapacity = 10;

    /** 线程最大空闲时间 */
    private int keepAliveSeconds = 3000;

    @Bean
    public Executor taskExecutor() {
        //支持并发，异步等操作
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        return executor;
    }




}
