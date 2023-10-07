package com.example.interview;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-09-04
 */
public class TestString {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        String s = new String("abc");
        System.out.println(s.hashCode());

        // 在中间可以添加N行代码，但是必须保证S引用的指向不变，最终将输出变成 "abcd"
        // 利用反射可以实现
        Field field = s.getClass().getDeclaredField("value");
        field.setAccessible(true);
        field.set(s, "abcd".toCharArray());
        System.out.println(s.hashCode());
        System.out.println(s);
    }

    @Test
    public void test2() throws Exception {
        System.out.println("===== test2 =====");
        // 2个对象, abc 是放在字符串常量池中的，s1放在堆中的
        String s1 = new String("abc");
        String s2 = "abc";
        // false
        System.out.println(s1 == s2);

        // String对象的intern 方法，首先会检查字符串常量池中是否存在"abc",如果存在则返回字符串引用；
        // 如果不存在，则把"abc"添加到字符串常量池中，并返回该字符串常量的引用
        String s3 = s1.intern();
        // true
        System.out.println(s3 == s2);
    }

    @Test
    public void test3() throws Exception {
        System.out.println("===== test3 =====");
        Integer i1 = 100;
        Integer i2 = 100;

        // true
        System.out.println(i1 == i2);

        Integer i3 = 128;
        Integer i4 = 128;
        // false
        System.out.println(i3 == i4);

        // 可以使用 -XX:AutoBoxCacheMax=256 来进行修改
    }

}
