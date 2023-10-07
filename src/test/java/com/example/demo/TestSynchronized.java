package com.example.demo;

import org.junit.Test;

/**
 * @author Chris
 * @date 2020/04/21 20:23
 * @since 1.0.0
 */
public class TestSynchronized {
    private int a = 0;

    @Test
    public void test1() {
        System.out.println("===== test1 =====");
        synchronized (this) {
            a++;
        }
        System.out.println(a);
        test();
    }

    private synchronized void test(){
        a++;
    }
}
