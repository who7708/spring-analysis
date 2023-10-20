package org.spring.boot.test.base;

import org.junit.Test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-07
 */
public class TestOverrideAndOverload {

    @Test
    public void test1() {
        System.out.println("===== test1 =====");
        test2(1, 2);
        test2(1);
        test2("1", "2");
        test2("1");
    }

    private void test2(int a, int b) {
        System.out.println(a + " , " + b);
    }

    private void test2(int a) {
        System.out.println(a);
    }

    private void test2(String a) {
        System.out.println(a);
    }

    private void test2(String a, String b) {
        System.out.println(a + " , " + b);
    }
}
