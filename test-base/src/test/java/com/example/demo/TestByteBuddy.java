package com.example.demo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/04/29
 */
public class TestByteBuddy {
    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        System.out.println("===== test1 =====");
        Class<?> dynamicType = new ByteBuddy()
                // subclass 指定了新创建的类的父类
                .subclass(Object.class)
                // method 指定了 Object 的 toString 方法
                .method(ElementMatchers.named("toString"))
                // intercept 拦截了 toString 方法并返回固定的 value
                .intercept(FixedValue.value("Hello World"))
                // 最后 make 方法生产字节码
                .make()
                // 由类加载器加载到虚拟机中
                .load(TestByteBuddy.class.getClassLoader())
                .getLoaded();

        Object instance = dynamicType.newInstance();
        String toString = instance.toString();
        System.out.println(toString);
        System.out.println(instance.getClass().getCanonicalName());
    }
}
