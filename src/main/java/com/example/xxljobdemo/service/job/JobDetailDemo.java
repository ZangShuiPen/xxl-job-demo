package com.example.xxljobdemo.service.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Author zhangsp
 * @Description
 * @Date 2023/12/15
 **/
@Slf4j
public class JobDetailDemo extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
            log.info("job的任务----> QuartzJobBean");
    }
}
