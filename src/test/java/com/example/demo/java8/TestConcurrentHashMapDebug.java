package com.example.demo.java8;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Chris
 * @date 2020/04/21 17:26
 * @since 1.0.0
 */
public class TestConcurrentHashMapDebug {
    @Test
    public void test1() {
        System.out.println("===== test1 =====");
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i, i);
        }
        map.put(101, 101);
        System.out.println(map);
    }
}
