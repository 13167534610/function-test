package com.function.javabean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * description: bean的扫描配置类
 *
 * @author wangqiang
 */
@Configuration
@ComponentScan(basePackageClasses = ClassInterface.class)
public class ClassConfig {
}
