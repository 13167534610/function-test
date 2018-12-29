package com.function.timmer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * spring自带的定时任务，配置在spring-application.xml
 */
public class JobImpl{
    @Scheduled(cron = "0/1 * *  * * ?")
    public void myJob() {
        System.out.println("任务执行中");
    }
}
