package com.function.javabean;

import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author wangqiang
 */
@Component
public class Class1 implements ClassInterface{

    public void print(){
        System.out.println("class1 bean 创建成功");
    }
}
