package com.function.aop;

/**
 * description:自定义通知类
 * @author wangqiang
 */
public class Annotation1 {

    //此通知在切入点方法执行前执行
    public void beforeMethod(){
        System.out.println("前置通知beforeMethod");
        System.out.println("+++++++++++++++++++++++++++++++++++");
    }
    //此通知无论切入点方法成功还是失败都执行
    public void afterMethod(){
        System.out.println("后置通知afterMethod");
        System.out.println("+++++++++++++++++++++++++++++++++++");
    }
    //此通知只有切入点方法成功执行后才会执行
    public void afterRuningMethod(){
        System.out.println("后置通知afterRuningMethod");
        System.out.println("+++++++++++++++++++++++++++++++++++");
    }
    //此通知只有切入点方法出异常才执行
    public void exceptionMehtod(){
        System.out.println("异常通知exceptionMehtod");
        System.out.println("+++++++++++++++++++++++++++++++++++");
    }
}
