package com.function.quartz;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 * Created by wangqiang on 2017/8/23.
 */
@Service
public class Job extends QuartzJobBean {
    private MyTask1 myTask;

    public MyTask1 getMyTask() {
        return myTask;
    }

    public void setMyTask(MyTask1 myTask) {
        this.myTask = myTask;
    }

    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
     myTask.test();
    }
}
