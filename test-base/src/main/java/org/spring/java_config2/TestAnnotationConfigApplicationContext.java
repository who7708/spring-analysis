package org.spring.java_config2;

import org.spring.java_config2.config.AppConfig;
import org.spring.java_config2.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/17
 */
public class TestAnnotationConfigApplicationContext {
    public static void main(String[] args) {
        // java 注解方式
        // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("org.spring.java_config2");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        // Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
        UserService userService = ctx.getBean(UserService.class);
        System.out.println(userService);
    }
}
