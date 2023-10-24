package org.spring.boot.test.jvm;

/**
 * 每个栈帧（Stack Frame）中存储着：
 * 1. 局部变量表（Local Variables）
 * 2. 操作数栈（Operand Stack）(或称为表达式栈)
 * 3. 动态链接（Dynamic Linking）：
 * 4. 指向运行时常量池的方法引用方法返回地址（Return Address）：方法正常退出或异常退出的地址
 * 5. 一些附加信息
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/24
 */
public class TestStackFrame {

    private static int method3() {
        System.out.println("method3 开始执行");
        // 局部变量表
        int i = 1;
        System.out.println("method3 结束执行");
        return i;
    }

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
}
