package com.example.xxljobdemo.quartz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.*;

/**
 * @Author zhangsp
 * @Description 任务模型
 * @Date 2023/12/15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobModel {

    private String group;
    private String name;
    private String cronExpression;
    private Class<? extends Job> jobClass;

    // 返回触发器TriggerKey
    public TriggerKey getTriggerKey(){
         TriggerKey triggerKey = TriggerKey.triggerKey(this.name, this.group);
         return triggerKey;
    }

    // 返回JobDetail,表示job的实现类
    public JobDetail getJonDetail(){
         JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(this.name, this.group).build();
         return jobDetail;
    }

    public CronTrigger getCronTrigger(){
         CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(this.cronExpression);
         CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(this.name, this.group).withSchedule(cronScheduleBuilder).build();
         return cronTrigger;
    }

    public JobModel addJobData(String key,String value){
        JobDataMap jobDataMap = this.getJonDetail().getJobDataMap();
        jobDataMap.put(key,value);
        return this;
    }


}
