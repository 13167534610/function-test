package com.function.aop;


import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * description: 实现spring的接口完成前置通知的功能
 * @author wangqiang
 */
public class BeforeAnnotation implements MethodBeforeAdvice {

    /**
     * description 前置通知方法
     * wangqiang
     * @param method 当前执行方法
     * @param objects 当前执行方法的参数数组
     * @param target 执行方法的目标类
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("前置通知：当前执行方法名："+method.getName());
        System.out.println("前置通知：参数个数为："+objects.length);
        System.out.println("前置通知：目标类："+target.getClass());
        System.out.println("+++++++++++++++++++++++++++++++++++");
    }
}
