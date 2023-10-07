package com.example.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Chris
 * @date 2020/05/13
 * @since 1.0.0
 */
public class TestLockSupport {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== test5 =====");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start ...");
                LockSupport.park();
                System.out.println("continue ...");
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        LockSupport.unpark(t);
        System.out.println("end ...");
    }
}
