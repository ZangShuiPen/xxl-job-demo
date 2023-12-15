package com.example.xxljobdemo.service.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @Author zhangsp
 * @Description
 * @Date 2023/12/15
 **/
@Slf4j
@Component
public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("开始任务");
        System.out.println(jobExecutionContext.getJobInstance());
        System.out.println("11111");
    }
}
