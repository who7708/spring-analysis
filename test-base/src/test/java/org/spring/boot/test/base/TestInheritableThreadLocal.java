package org.spring.boot.test.base;

import org.junit.Test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-09
 */
public class TestInheritableThreadLocal {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        InheritableThreadLocal<String> local = new InheritableThreadLocal<>();
        local.set("word");
        InheritableThreadLocal<String> local2 = new InheritableThreadLocal<>();
        local2.set("hello");
        //
        System.out.println(local.get());
        System.out.println(local2.get());

        Thread thread = new Thread(() -> {
            System.out.println(local.get());
            System.out.println(local2.get());

            local.set("word2");
            local2.set("hello2");
            //
            System.out.println(local.get());
            System.out.println(local2.get());
        }, "test_ThreadLocal");
        thread.start();
        thread.join();
        //
        System.out.println(local.get());
        System.out.println(local2.get());
    }
}
