package com.example.demo.thread;

import org.junit.Test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/10 010
 */
public class TestThreadLocal2 {
    public static final InheritableThreadLocal<String> NAME = new InheritableThreadLocal<>();
    // public static final ThreadLocal<String> NAME = new ThreadLocal<>();

    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        NAME.set("HelloWorld");
        //
        Thread childThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // NAME.set("HelloJava");
                // 使用ThreadLocal 时，返回null
                // 使用InheritableThreadLocal 时,返回 HelloWorld
                System.out.println(NAME.get());
            }
        });
        NAME.set("HELLO JAVA");
        childThread.start();
        // join 原理
        // while (isAlive()) {
        //     wait(0);
        // }
        // 会先判断当前调用join的线程是否存活,即当前线程是否执行结束,如果没有执行结束,则进行Object.wait进行阻塞
        childThread.join();
    }

    @Test
    public void test2() throws Exception {
        System.out.println("===== test2 =====");
        Test1 test1 = new Test1();
        test1.a("Hello Java");
    }

    static class Test1 {
        public static final ThreadLocal<String> NAME = new ThreadLocal<>();

        public void a(String name) {
            NAME.set(name);
            b();
        }

        private void b() {
            c();
            System.out.println(NAME.get());
        }

        private void c() {
            System.out.println(NAME.get());
        }

    }
}
