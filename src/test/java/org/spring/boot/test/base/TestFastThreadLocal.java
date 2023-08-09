package org.spring.boot.test.base;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;
import org.junit.Test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-09
 */
public class TestFastThreadLocal {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");

        FastThreadLocal<String> fastThreadLocal = new FastThreadLocal<>();
        fastThreadLocal.set("hello");
        System.out.println(fastThreadLocal.get());
        System.out.println("========");
        Thread thread = new FastThreadLocalThread(() -> {
            System.out.println(fastThreadLocal.get());

            fastThreadLocal.set("word2");

            System.out.println(fastThreadLocal.get());
        }, "test_ThreadLocal");
        thread.start();
        thread.join();

        System.out.println(fastThreadLocal.get());
    }
}
