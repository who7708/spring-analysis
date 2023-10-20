package org.spring.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("===============before advice start==============");
        System.out.println("method: " + method);
        System.out.println("args: " + Arrays.toString(args));
        System.out.println("target: " + target);
        System.out.println("===============before advice end================");
    }

}