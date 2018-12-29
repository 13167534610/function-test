package com.function.aop;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description:任务类
 *
 * @author wangqiang
 */
public class Task {

    @ResponseBody
    public Integer show(String name){
        Integer age = 18;
        System.out.println("==============================");
        System.out.println("task is running");
        //throw new RuntimeException("出异常了");
        System.out.println("==============================");
        return age;
    }
}
