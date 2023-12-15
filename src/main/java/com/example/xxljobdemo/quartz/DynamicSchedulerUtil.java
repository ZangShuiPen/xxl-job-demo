package com.example.xxljobdemo.quartz;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * @Author zhangsp
 * @Description
 * @Date 2023/12/15
 **/
@Slf4j
@Component
public class DynamicSchedulerUtil implements InitializingBean {


    private static Scheduler scheduler;

    public static void setScheduler(Scheduler scheduler){
        DynamicSchedulerUtil.scheduler=scheduler;
    }
    public static Scheduler getScheduler(){
       return DynamicSchedulerUtil.scheduler;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (ObjectUtils.isEmpty(scheduler)){
            StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler newScheduler = schedulerFactory.getScheduler();
            scheduler=newScheduler;
        }
    //    Assert.notNull(scheduler,"scheduler is not null");
        log.info(String.format("========== 初始化DynamicSchedulerUtil 的scheduler是：%s",scheduler));
    }

    // 开启
    public static boolean addJob(JobModel jobModel) throws SchedulerException {
        final TriggerKey  triggerKey= jobModel.getTriggerKey();
        if (scheduler.checkExists(triggerKey)){
            Trigger trigger= scheduler.getTrigger(triggerKey);
            log.info(String.format(">>>>>>>>>>>>Alreay exit trigger[%s] by triggerKey [%s]",trigger,triggerKey ));
            return false;
        }
        final CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobModel.getCronExpression());
        final CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
        final JobDetail jonDetail = jobModel.getJonDetail();
        final Date date = scheduler.scheduleJob(jonDetail, cronTrigger);
        log.info(String.format(">>>>>>>>>>>>>Register DyNamicJob %s on data %S",jobModel,date));
        return true;
    }

    //暂停
    public static boolean pauseJob(JobModel exitJob) throws SchedulerException {
         TriggerKey triggerKey = exitJob.getTriggerKey();
         boolean result= false;
         if (scheduler.checkExists(triggerKey)){
             scheduler.pauseTrigger(triggerKey);
             log.info(String.format(">>>>>>>> Pause exit DynamicJon: exitjob[%s],exitTrigger[%s]",exitJob,triggerKey));
             result=true;
         }else {
             log.info(String.format(">>>>>>>> Pause exit DynamicJon: exitjob[%s],exitTrigger[%s]",exitJob,triggerKey));

         }
        return result;
    }
}
