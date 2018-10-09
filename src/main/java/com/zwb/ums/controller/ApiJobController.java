package com.zwb.ums.controller;

import com.entcms.sdk.exception.ENTCMSException;
import com.github.pagehelper.PageInfo;
import com.zwb.ums.job.BaseJob;
import com.zwb.ums.model.po.JobAndTrigger;
import com.zwb.ums.model.po.MyJob;
import com.zwb.ums.service.JobAndTriggerService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: ApiJobController
 * </p>
 * <p>
 * Description: 定时任务Controller
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/31 10:33
 */
@Controller
public class ApiJobController {

    @Autowired
    private JobAndTriggerService jobAndTriggerService;

    //加入Qulifier注解，通过名称注入bean
    @Autowired @Qualifier("scheduler")
    private Scheduler scheduler;

    private static Logger log = LoggerFactory.getLogger(ApiJobController.class);

    /**
     * @Title: addjob
     * @Description: 添加定时任务
     * @param: myJob 请求体接收对象 用于接收axios提交的json数据
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/31 10:53
     * @version 1.0
     */
    @RequestMapping("/api/job/add")
    @ResponseBody
    public Map<String, Object> addJob(@RequestBody MyJob myJob ) throws Exception {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        addJob(myJob.getJobClassName(), myJob.getJobGroup(), myJob.getCronExpression());
        rootMap.put("code", "100");
        rootMap.put("mesg", "添加任务成功！");
        return rootMap;
    }

    /**
     * @Title: addJob
     * @Description: 创建定时任务
     * @param: jobClassName 执行任务的类名全称
     * @param: jobGroupName 任务分组名
     * @param: cronExpression cron表达式
     * @return:
     * @author zhouwenbo
     * @date 2018/5/31 10:54
     * @version 1.0
     */
    public void addJob(String jobClassName, String jobGroupName, String cronExpression)throws Exception{
        // 启动调度器
        scheduler.start();
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败"+e);
            throw new Exception("创建定时任务失败");
        }
    }

    /**
     * @Title: queryJobListAndPage
     * @Description: 分页查询任务列表数据
     * @param: 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/31 10:57
     * @version 1.0
     */
    @RequestMapping("/api/job/query/list/{pageNum}")
    @ResponseBody
    public Map<String, Object> queryJobListAndPage(MyJob myJob, @PathVariable("pageNum")int pageNum,
                                                   @RequestParam(value="pageSize",defaultValue="10")int pageSize) throws ENTCMSException {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        PageInfo<JobAndTrigger> jobAndTrigger = jobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
        if(null != jobAndTrigger.getList() && jobAndTrigger.getList().size() > 0){
            rootMap.put("code", "100");
            rootMap.put("mesg", "查询成功");
            rootMap.put("data", jobAndTrigger.getList());
            rootMap.put("pageNum", jobAndTrigger.getPageNum() );
            rootMap.put("pageSize", jobAndTrigger.getPageSize() );
            rootMap.put("pageCount", jobAndTrigger.getTotal());
            return rootMap;
        }
        rootMap.put("code", "999");
        rootMap.put("mesg", "查询失败！");
        return rootMap;
    }

    /**
     * @Title: pausejob
     * @Description: 暂停任务
     * @param: myJob 请求体接收对象 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/31 11:07
     * @version 1.0
     */
    @RequestMapping("/api/job/pause")
    @ResponseBody
    public Map<String, Object> pauseJob(@RequestBody MyJob myJob ) {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        try {
            jobPause(myJob.getJobClassName(), myJob.getJobGroup());
            rootMap.put("code", "100");
            rootMap.put("mesg", "任务暂停成功！");
        }catch (Exception e){
            rootMap.put("code", "999");
            rootMap.put("mesg", "出现异常,异常原因:"+e.getMessage());
        }
        return rootMap;
    }

    /**
     * @Title: jobPause
     * @Description: 暂停定时任务
     * @param: jobClassName 执行任务的类名全称
     * @param: jobGroupName 任务分组名
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/31 11:08
     * @version 1.0
     */
    public void jobPause(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    /**
     * @Title: resumeJob
     * @Description: 恢复任务的执行
     * @param: myJob
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/31 11:15
     * @version 1.0
     */
    @RequestMapping("/api/job/resume")
    @ResponseBody
    public Map<String, Object> resumeJob(@RequestBody MyJob myJob ) {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        try {
            jobResume(myJob.getJobClassName(), myJob.getJobGroup());
            rootMap.put("code", "100");
            rootMap.put("mesg", "任务恢复成功！");
        }catch (Exception e){
            rootMap.put("code", "999");
            rootMap.put("mesg", "出现异常,异常原因:"+e.getMessage());
        }
        return rootMap;
    }

    /**
     * @Title: jobresume
     * @Description: 恢复定时任务
     * @param: jobClassName 执行任务的类名全称
     * @param: jobGroupName 任务分组名
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/31 11:16
     * @version 1.0
     */
    public void jobResume(String jobClassName, String jobGroupName) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    /**
     * @Title: rescheduleJob
     * @Description: 重新安排任务
     * @param: myJob
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/31 11:20
     * @version 1.0
     */
    @RequestMapping("/api/job/reschedule")
    @ResponseBody
    public Map<String, Object> rescheduleJob(@RequestBody MyJob myJob ) {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        try {
            jobReschedule(myJob.getJobClassName(), myJob.getJobGroup(), myJob.getCronExpression());
            rootMap.put("code", "100");
            rootMap.put("mesg", "任务更新成功！");
        }catch (Exception e){
            rootMap.put("code", "999");
            rootMap.put("mesg", "出现异常,异常原因:"+e.getMessage());
        }
        return rootMap;
    }

    public void jobReschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败"+e);
            throw new Exception("更新定时任务失败");
        }
    }

    /**
     * @Title: deleteJob
     * @Description: 删除定时任务
     * @param: myJob
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/31 11:30
     * @version 1.0
     */
    @RequestMapping("/api/job/delete")
    @ResponseBody
    public Map<String, Object> deleteJob(@RequestBody MyJob myJob ) {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        try {
            jobDelete(myJob.getJobClassName(), myJob.getJobGroup());
            rootMap.put("code", "100");
            rootMap.put("mesg", "任务删除成功！");
        }catch (Exception e){
            rootMap.put("code", "999");
            rootMap.put("mesg", "出现异常,异常原因:"+e.getMessage());
        }
        return rootMap;
    }

    public void jobDelete(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob)class1.newInstance();
    }


}
