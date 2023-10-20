package org.spring.java_config2.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/17
 */
// @Aspect
// @Component
public class TestAspect {
    @Before("execution(public void org.spring.java_config2.service.UserService.test())")
    public void testBefore() {
        System.out.println("testBefore");
    }
}
