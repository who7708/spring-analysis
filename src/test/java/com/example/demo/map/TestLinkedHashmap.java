package com.example.demo.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Chris
 * @date 2020/05/14
 * @since 1.0.0
 */
public class TestLinkedHashmap {
    public static void main(String[] args) {
        // 基于LinkedHashMap 的访问顺序的特点，可构造一个LRU（Least Recently Used） 最近最少使用简单缓存。
        // 也有一些开源的缓存产品如ehcache 的淘汰策略（ LRU ）就是在LinkedHashMap 上扩展的。
        Map<String, String> linkedHashMap = new LinkedHashMap<>(16, 0.75F, true);
        // Map<String, String> linkedHashMap = new HashMap<>();
        linkedHashMap.put("key_2", "value_2");
        linkedHashMap.put("key_1", "value_1");
        linkedHashMap.put("key_4", "value_4");
        linkedHashMap.put("key_3", "value_3");

        linkedHashMap.put("key_3", "value_3");
        linkedHashMap.put("key_4", "value_4");
        linkedHashMap.put("key_1", "value_1");
        linkedHashMap.put("key_2", "value_2");
        // {key_3=value_3, key_4=value_4, key_1=value_1, key_2=value_2}
        System.out.println(linkedHashMap);

        int[] a = new int[2];
        double[] b = new double[2];
        float[] c = new float[2];
        String[] d = new String[2];

        System.out.println("end...");
    }
}
