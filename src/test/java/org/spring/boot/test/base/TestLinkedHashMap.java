package org.spring.boot.test.base;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
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

    @Test
    public void testLRUCache() {
        System.out.println("===== test1 =====");
        int initialCapacity = 10;
        LinkedHashMap<String, String> map = new LRUCache<>(initialCapacity);
        for (int i = 1; i <= initialCapacity; i++) {
            map.put("key_" + i, "value_" + i);
            System.out.println(map.get("key_1"));
        }
        map.put("key_11", "value_11");
        map.put("key_12", "value_12");
        System.out.println("已装载");
        System.out.println(map.get("key_2"));
        System.out.println(map.get("key_3"));
        System.out.println(map.get("key_4"));
    }
}

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int MAX_CAPACITY;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        MAX_CAPACITY = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_CAPACITY;
    }
}

