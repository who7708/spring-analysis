package org.spring.boot.test.demo;

import java.util.concurrent.CountDownLatch;

/*
 *
 * */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 1; i <= 2; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i));
            thread.start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t *****班长最后关门走人");
    }
}
