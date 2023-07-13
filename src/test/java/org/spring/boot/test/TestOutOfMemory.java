package org.spring.boot.test;

import org.junit.Test;

import java.util.Timer;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-13
 */
public class TestOutOfMemory {

    @Test
    public void testForOOM() throws InterruptedException {
        System.out.println("===== testForOOM =====");
        Timer timer = new Timer();
        timer.schedule(new MyOutOfMemoryTask(), 10000);
        Thread.currentThread().join();
    }

}
