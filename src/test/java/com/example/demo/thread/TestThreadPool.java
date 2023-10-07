package com.example.demo.thread;

import org.spring.utils.MusicUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/06/21
 */
public class TestThreadPool {
    private static final AtomicInteger FINISHED = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            MusicUtils.executor.execute(TestThreadPool::downloadMusic);
        }

        while (true) {
            try {
                if (MusicUtils.executor.getActiveCount() <= 0 && !MusicUtils.executor.isShutdown()) {
                    System.out.println("executor shutdown...");
                    MusicUtils.executor.shutdown();
                    break;
                }
                TimeUnit.SECONDS.sleep(MusicUtils.time(3));
            } catch (Throwable ignore) {
            }
        }

        System.out.println("run finished by main...");

        // new Thread(() -> System.out.println("helloword")).start();
    }

    private static void downloadMusic() {
        // 长时间出错会导致线程池一直堵塞。原因是因为 Queue.take()方法
        try {
            TimeUnit.SECONDS.sleep(MusicUtils.time(3));
            System.out.println("download music finished..." + FINISHED.incrementAndGet());
        } catch (Throwable ignore) {
            System.out.println("线程出现错误");
            Thread.currentThread().interrupt();
        }

    }

}
