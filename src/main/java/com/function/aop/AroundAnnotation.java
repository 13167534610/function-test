package com.function.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * description:实现spring的接口完成环绕通知
 *      基于JDK的方法拦截器
 * @author wangqiang
 */
public class AroundAnnotation implements MethodInterceptor {

    /**
     * description:实现spring的接口完成环绕通知
     * @author wangqiang
     * @param methodInvocation 当前执行方法对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("环绕通知：执行方法之前");
        //获取原本要调用的方法的参数值数组
        Object[] arguments = methodInvocation.getArguments();
        //方法对象
        Method method = methodInvocation.getMethod();
        //方法名
        String name = method.getName();
        //获取方法上指定类型的注解对象
        ResponseBody annotation = method.getAnnotation(ResponseBody.class);

        //执行原本要调用的方法 返回值为原本要调用的方法的返回值
        Object proceed = methodInvocation.proceed();

        //获取方法上的注解对象数组
        Annotation[] annotations = method.getDeclaredAnnotations();
        //获取方法所属的类的类型
        Class<?> aClass = method.getDeclaringClass();
        //返回由此 Method 实例表示的注释成员的默认值
        Object defaultValue = method.getDefaultValue();
        //获取方法返回值类型
        Class<?> returnType = method.getReturnType();
        System.out.println(proceed);
        System.out.println("环绕通知：执行方法之后");
        return proceed;
    }
}
