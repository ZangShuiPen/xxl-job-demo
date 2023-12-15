package com.example.xxljobdemo;

import com.example.xxljobdemo.quartz.DynamicSchedulerUtil;
import com.example.xxljobdemo.quartz.JobModel;
import com.example.xxljobdemo.service.job.TestJob;
import org.junit.jupiter.api.Test;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class XxlJobDemoApplicationTests {

    @Test
    void contextLoads() throws SchedulerException, InterruptedException {
         JobModel jobModel = new JobModel();
         jobModel.setName("test");
         jobModel.setCronExpression("0/2 * * * * ?" );
         jobModel.setJobClass(TestJob.class);
//         StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
//         Scheduler scheduler = schedulerFactory.getScheduler();
//         DynamicSchedulerUtil.setScheduler(scheduler);
        boolean b = DynamicSchedulerUtil.addJob(jobModel);
   //     DynamicSchedulerUtil.getScheduler().start();
        TimeUnit.SECONDS.sleep(30);
        System.out.println(b);
    }

}
