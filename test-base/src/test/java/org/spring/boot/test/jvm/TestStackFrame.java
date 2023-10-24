package org.spring.boot.test.jvm;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/24
 */
public class TestStackFrame {
    public static void main(String[] args) {
        System.out.println("main 开始执行");
        method1();
        System.out.println("main 结束执行");
    }

    private static void method1() {
        System.out.println("method1 开始执行");
        method2();
        System.out.println("method1 结束执行");
    }

    private static void method2() {
        System.out.println("method2 开始执行");
        method3();
        System.out.println("method2 结束执行");
    }

    private static int method3() {
        System.out.println("method3 开始执行");
        int i = 1;
        System.out.println("method3 结束执行");
        return i;
    }
}
