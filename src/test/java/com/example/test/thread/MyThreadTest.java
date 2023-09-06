package com.example.test.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/09/06
 */
public class MyThreadTest {

    @Test
    public void testMyThread() throws InterruptedException {
        System.out.println("===== testMyThread =====");
        final MyThread myThread = new MyThread();
        myThread.start();
        myThread.join();
    }

    @Test
    public void testMyRunnable() throws InterruptedException {
        System.out.println("===== testMyRunnable =====");
        final MyRunnable myRunnable = new MyRunnable();
        final Thread myThread = new Thread(myRunnable);
        myThread.start();
        myThread.join();
    }

    @Test
    public void testMyCallable() throws Exception {
        System.out.println("===== testMyCallable =====");
        final MyCallable myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        final Thread myThread = new Thread(futureTask);
        myThread.start();
        // myThread.join();
        // 阻塞
        final Integer count = futureTask.get();
        System.out.println(count);
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("MyThread" + i);
            }
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("MyRunnable" + i);
            }
        }
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int count = 0;
            for (int i = 0; i < 100; i++) {
                count++;
            }
            return count;
        }
    }
}
