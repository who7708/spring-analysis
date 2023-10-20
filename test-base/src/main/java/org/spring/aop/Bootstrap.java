package org.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * AOP测试启动类.
 *
 * @author skywalker
 */
public class Bootstrap {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        System.out.println("==========================");
        // 打印所有加载的bean
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println("==========================");
        SimpleAopBean bean = context.getBean(SimpleAopBean.class);
        bean.testB();
        System.out.println(bean.getClass().getSimpleName());
    }

}
