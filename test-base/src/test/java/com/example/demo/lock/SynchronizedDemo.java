package com.example.demo.lock;

import java.util.concurrent.TimeUnit;

public class SynchronizedDemo {
    public static void main(String[] args) {
        synchronized (SynchronizedDemo.class) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            method();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 在 main 线程释放锁之前，无法执行。因为 synchronizated 锁住的是类对象(SynchronizedDemo.class)
                method();
            }
        }, "thread1").start();
    }

    private static void method() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " hello method");
    }
}
