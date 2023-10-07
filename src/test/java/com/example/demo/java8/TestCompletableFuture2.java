package com.example.demo.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * https://www.ibm.com/developerworks/cn/java/j-cf-of-jdk8/index.html
 *
 * @author Chris
 * @date 2020/04/24
 * @since 1.0.0
 */
public class TestCompletableFuture2 {
    @Test
    public void test1() {
        System.out.println("===== test1 =====");
        thenAcceptAsyncExample();
    }

    static void completedFutureExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }

    static void runAsyncExample() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });
        assertFalse(cf.isDone());
        sleepEnough();
        assertTrue(cf.isDone());
    }

    static void randomSleep() {
        try {
            TimeUnit.SECONDS.sleep((long) (Math.random() * 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void sleepEnough() {
        try {
            TimeUnit.SECONDS.sleep((long) (Math.random() * 5) + 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(result::append);
        cf.join();
        assertTrue("Result was empty", result.length() > 0);
    }
}
// CompletableFuture可以指定异步处理流程：
//
// thenAccept()处理正常结果；
// exceptional()处理异常结果；
// thenApplyAsync()用于串行化另一个CompletableFuture；
// anyOf()和allOf()用于并行化多个CompletableFuture。