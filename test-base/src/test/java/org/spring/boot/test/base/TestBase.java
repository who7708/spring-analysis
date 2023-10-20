package org.spring.boot.test.base;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-07
 */
public class TestBase {
    public int compute() {
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        TestBase test = new TestBase();
        int result = test.compute();
        System.out.println(result);
    }
}
