package com.function.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description:测试spring的面向切面编程 AOP
 * @author wangqiang
 */
public class TestAop {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop.xml");
        //不能手动创建task对象，因为创建的与beanFactory生成的不是同一个对象，无法切入
        Task task = (Task) context.getBean("task");
        task.show("wq");
    }
}
