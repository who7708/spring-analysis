package com.example.demo.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @date 2020/05/13
 * @since 1.0.0
 */
public class TestThreadLocal {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        System.out.println("start...");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(tl.get());
                tl.set(new Person());
                tl.set(new Person());
                tl.set(new Person());
                System.out.println(this);
                System.out.println(tl);
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                tl.set(new Person());
                tl.set(new Person());
                tl.set(new Person());
                System.out.println(this);
                System.out.println(tl);
            }
        });
        thread2.start();
        System.out.println(tl);
    }

    static class Person {
        String name = "zhangsan";
    }
}
