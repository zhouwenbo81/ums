package com.zwb.ums.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <p>
 * Title: DruidConfig
 * </p>
 * <p>
 * Description: Druid连接池Config
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/3/15 13:40
 */
@Configuration
public class DruidConfig {

    /**
     *  配置DruidDataSource Bean,使其成为一个Spring Bean
     *  @ConfigurationProperties :作用，
     *  将外部的配置文件application.properties与我们的Class DruidDataSource的数据关系进行绑定
     *  DruidDataSource也会成为一个spring Bean
     *  2. 可以将Bean方法的返回对象与配置文件进行绑定
     *  即我们在配置文件中写上 spring.druid开头的配置信息，
     *  spring会自动的将这些信息写入到DruidDataSource的属性中
     *
     *  initMethod = "init":spring boot 容器启动之后，DruidDataSource也会跟随启动
     *  destroyMethod = "close" :容器销毁的时候去关闭数据连接
     * @return
     */
    @ConfigurationProperties(prefix = "spring.druid")
    @Bean(initMethod = "init",destroyMethod = "close")
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        //将慢日志功能加入到连接池中
        dataSource.setProxyFilters(Lists.newArrayList(statFilter()));
        return dataSource;
    }

    /**
     * 将慢日志打印出来，追踪较慢的sql
     * @return
     */
    @Bean
    public Filter statFilter(){
        StatFilter statFilter = new StatFilter();
        // 配置慢日志的时间 大于5秒的sql为慢sql
        statFilter.setSlowSqlMillis(5000);
        //是否打印慢sql
        statFilter.setLogSlowSql(true);
        // 是否将日志合并
        statFilter.setMergeSql(true);
        return statFilter;
    }

    /**
     * 添加Druid的监控
     * 监控mysql
     * 监控每一个sql的执行时间
     * 执行时间的分布
     * 执行操作记录的分布
     *
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
    }


}
