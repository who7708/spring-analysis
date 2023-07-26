package org.spring.boot.test;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-26
 */
public class TestLinkedHashMap {
    @Test
    public void testLinkedHashMap() {
        System.out.println("===== test1 =====");
        int initialCapacity = 2;
        LinkedHashMap<String, String> map = new LinkedHashMap<>(initialCapacity);
        for (int i = 1; i <= initialCapacity; i++) {
            map.put("key_" + i, "value_" + i);
        }
        System.out.println("已装载");
        map.put("key_" + (initialCapacity + 1), "value_" + (initialCapacity++));

        String aa = map.get("key_" + initialCapacity);
        System.out.println(aa);
    }

    @Test
    public void testTreeMap() {
        System.out.println("===== test1 =====");
        int initialCapacity = 2;
        TreeMap<String, String> map = new TreeMap<>();
        for (int i = 1; i <= initialCapacity; i++) {
            map.put("key_" + i, "value_" + i);
        }
        System.out.println("已装载");
        map.put("key_" + (initialCapacity + 1), "value_" + (initialCapacity++));

        String aa = map.get("key_" + initialCapacity);
        System.out.println(aa);
    }
}
