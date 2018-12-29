package com.function.timmer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by wangqiang on 2017/8/23.
 */
/*@ContextConfiguration("classpath:spring-basic.xml")*/
public class Test {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("spring-application.xml");
        //System.out.println("1111");
        System.in.read();
    }
}
