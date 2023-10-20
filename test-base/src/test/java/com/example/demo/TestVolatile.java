package com.example.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @date 2020/04/21 21:58
 * @since 1.0.0
 */
public class TestVolatile {
    volatile
    boolean running = true;

    void m() {
        System.out.println("m start");
        while (running) {

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        final TestVolatile t = new TestVolatile();
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        }, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
