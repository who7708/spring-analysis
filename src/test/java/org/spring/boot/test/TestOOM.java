package org.spring.boot.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-24
 */
public class TestOOM {
    public static void main(String[] args) {
        Map<Integer, Object> m = new HashMap<>();
        for (int i = 0; i < 10_000; i++) {
            byte[] bytes = new byte[1024 * 1024];
            m.put(i, bytes);
        }
        System.out.println("finish oom..." + m.size());
    }
}
