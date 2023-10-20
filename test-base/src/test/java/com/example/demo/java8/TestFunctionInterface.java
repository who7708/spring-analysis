package com.example.demo.java8;

import org.junit.Test;

/**
 * @author Chris
 * @date 2020/04/24
 * @since 1.0.0
 */
public class TestFunctionInterface {
    @Test
    public void test1() {
        System.out.println("===== test1 =====");

        TestA test = new TestA();
        test.operate(1, 2, new Operation() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        });

    }

    private class TestA {
        private int operate(int a, int b, Operation operation) {
            return operation.operation(a, b);
        }
    }

}
