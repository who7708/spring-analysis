package org.spring.boot.test.base;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-08
 */
public class TestThreadLocal {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        ThreadLocal<String> local = new ThreadLocal<>();
        //设置值
        local.set("word");
        local.set("word2");
        ThreadLocal<String> local2 = new ThreadLocal<>();
        //设置值
        local2.set("hello");
        local2.set("hello2");
        //获取刚刚设置的值
        System.out.println(local.get());

        for (int i = 0; i < 8; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    local.set("Hello_" + finalI);
                }
            }).start();
        }

    }

    @Test
    public void test2() throws Exception {
        System.out.println("===== test1 =====");
        ThreadLocal<String> local = new ThreadLocal<>();
        ThreadLocal<String> local2 = new ThreadLocal<>();
        Thread thread = new Thread(() -> {
            local.set("word");
            local.set("word2");
            local2.set("hello");
            local2.set("hello2");
            // hello2 world2
            System.out.println(local.get());
            System.out.println(local2.get());
        }, "test_ThreadLocal");
        thread.start();
        thread.join();
        // null
        System.out.println(local.get());
        System.out.println(local2.get());

    }

    @Test
    public void testNextHashCode() throws Exception {
        System.out.println("===== test2 =====");
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("Hello");
        // Method nextHashCode = ThreadLocal.class.getDeclaredMethod("nextHashCode");
        Method nextHashCode = local.getClass().getDeclaredMethod("nextHashCode");
        nextHashCode.setAccessible(true);
        int invoke1 = (int) nextHashCode.invoke(local);
        int invoke2 = (int) nextHashCode.invoke(local);
        int invoke3 = (int) nextHashCode.invoke(local);
        int invoke4 = (int) nextHashCode.invoke(local);
        System.out.println(invoke1);
        System.out.println(invoke1 & 15);
        System.out.println(invoke2);
        System.out.println(invoke2 & 15);
        System.out.println(invoke3);
        System.out.println(invoke3 & 15);
        System.out.println(invoke4);
        System.out.println(invoke4 & 15);
    }
}
