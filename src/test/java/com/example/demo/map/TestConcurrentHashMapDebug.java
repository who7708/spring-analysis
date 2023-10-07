package com.example.demo.map;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMapDebug8;
import java.util.Map;

/**
 * @author Chris
 * @date 2020/04/21 17:26
 * @since 1.0.0
 */
public class TestConcurrentHashMapDebug {
    @Test
    public void test1() {
        System.out.println("===== test1 =====");

        Map<Integer, Integer> map = new ConcurrentHashMapDebug8<>();
        for (int i = 0; i < 100; i++) {
            map.put(i, i);
        }
        map.put(101, 101);
        System.out.println(map);
    }
}
