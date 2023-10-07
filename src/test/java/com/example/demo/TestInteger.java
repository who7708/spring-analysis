package com.example.demo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/04/21
 */
public class TestInteger {
    @Test
    public void test1() {
        System.out.println("===== test1 ====");
        Integer f1 = 100,
                f2 = 100,
                f3 = 150,
                f4 = 150;
        System.out.println(f1 == f2);
        System.out.println(f3 == f4);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.remove(3);
        list.clear();
    }

    // java 如何让 a == 1 && a== 2 && a == 3 成立?
    @Test
    public void test4() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("========== 测试方法 test4 ==========");

        Class<?> declaringClass = Integer.class.getDeclaringClass();

        Class cache = Integer.class.getDeclaredClasses()[0];
        Field cacheField = cache.getDeclaredField("cache");
        cacheField.setAccessible(true);
        Integer[] array = (Integer[]) cacheField.get(cache);
        // array[129] = 1
        array[130] = array[129];
        // Set 2 to be 1
        array[131] = array[129];
        // int a = 1;
        Integer a = 1;
        // Set 3 to be 1
        // 原理: (Integer)1 will call `Integer valueOf(int i)` and IntegerCache
        // if (a == 1 && a == 2 && a == 3) {
        if (a == (Integer) 1 && a == (Integer) 2 && a == (Integer) 3) {
            System.out.println("success");
        }
    }
}
