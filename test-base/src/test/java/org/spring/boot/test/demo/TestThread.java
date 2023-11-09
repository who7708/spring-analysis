package org.spring.boot.test.demo;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 线程创建的几种方式
 * 1. 继承Thead类，实现run方法
 * 2. 实现Runnable 接口，实现run方法，再
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/24
 */
public class TestThread {
    @Test
    public void testExtendsThread() throws Exception {
        System.out.println("===== testExtendsThread =====");
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("继承 Thread 类");
            }
        };
        thread.start();
        thread.join();
    }

    @Test
    public void testImplementsRunnable() throws Exception {
        System.out.println("===== testImplementsRunnable =====");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现 Runnable 接口");
            }
        });
        thread.start();
        thread.join();
    }

    @Test
    public void testFutureTask() throws Exception {
        System.out.println("===== testFutureTask =====");
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "testFutureTask";
            }
        });

        // FutureTask<String> task = new FutureTask<>(new Runnable() {
        //     @Override
        //     public void run() {
        //         System.out.println("Runnable.run");
        //     }
        // }, "result");
        Thread thread = new Thread(task);
        thread.start();
        // thread.join();
        System.out.println("task.get() = " + task.get());
    }

    @Test
    public void testThreadPool() throws Exception {
        System.out.println("===== testThreadPool =====");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("使用线程池");
            }
        });
        if (executorService.isTerminated()) {
            wait();
        }
    }

}
