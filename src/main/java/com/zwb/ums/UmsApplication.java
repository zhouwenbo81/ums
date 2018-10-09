package com.zwb.ums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @MapperScan("com.zwb.ums.mapper")
 * 将项目中对应的mapper类的路径加进来
 * 如果不使用该注解，可以在需要引入的Mapper接口上添加@Mapper注解
 *
 * @EnableScheduling :开启任务调度功能
 */
@SpringBootApplication
@MapperScan("com.zwb.ums.mapper")
@EnableScheduling
public class UmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmsApplication.class, args);
	}
}
