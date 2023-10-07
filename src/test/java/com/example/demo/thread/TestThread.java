package com.example.demo.thread;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-29
 */
public class TestThread {
    @Test
    public void testThread() throws Exception {
        System.out.println("===== testThread =====");
        Thread thread = new Thread(() -> {
            try {
                // interruptStop();
                exceptionStop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.wait();
        thread.start();
        // thread.join();
        TimeUnit.SECONDS.sleep(100);
    }


    private static void interruptStop() throws Exception {
        // 长时间出错会导致线程池一直堵塞。原因是因为 Queue.take()方法
        // try {
        while (true) {
            // 如果没有 sleep 也没有 break 则会一直执行，即使调用了 interrupt 方法
            // TimeUnit.SECONDS.sleep(1);
            int i = RandomUtils.nextInt(0, 100);
            System.out.println(i);
            if (i % 5 == 0) {
                Thread.currentThread().interrupt();
                System.out.println("线程出现错误");
                break;
            }
            // } catch (Throwable ignore) {
            //     System.out.println("线程出现错误");
            //     Thread.currentThread().interrupt();
            // }
        }
        // // 不在 while 中增加 break, 则无法执行到此处
        // System.out.println("线程结束");
    }

    private static void exceptionStop() throws Exception {
        // 长时间出错会导致线程池一直堵塞。原因是因为 Queue.take()方法
        // try {
        while (true) {
            // TimeUnit.SECONDS.sleep(1);
            int i = RandomUtils.nextInt(0, 100);
            System.out.println(i);
            if (i % 5 == 0) {
                throw new Exception("线程内抛出异常");
            }
            // } catch (Throwable ignore) {
            //     System.out.println("线程出现错误");
            //     Thread.currentThread().interrupt();
            // }
        }
        // // 不在 while 中增加 break, 则无法执行到此处
        // System.out.println("线程结束");
    }


}
