package com.function.javabean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * description:
 *
 * @author wangqiang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClassConfig.class)
public class BeanTest {
    @Autowired
    private Class1 class1;
    @Autowired
    private Class2 class2;
    @Test
    public void test(){
        class1.print();
        class2.print();
    }
}
