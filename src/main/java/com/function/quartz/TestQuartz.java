package com.function.quartz;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by wangqiang on 2017/8/23.
 */
public class TestQuartz {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("spring-quartz.xml");
        System.out.println("1111");
        System.in.read();
    }
}
