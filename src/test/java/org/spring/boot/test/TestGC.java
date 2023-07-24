package org.spring.boot.test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-24
 */
public class TestGC {
    public static void main(String[] args) {
        for (int i = 0; i < 10_000; i++) {
            byte[] bytes = new byte[1024 * 1024];
            System.out.println("循环 " + (i + 1) + " 次, 大小 " + bytes.length);
        }
    }
}

// java -ea -Xms20m -Xmx30m TestGC