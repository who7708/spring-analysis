package com.example.demo.queue;

import org.junit.Test;
import org.spring.utils.MusicUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2021/02/03
 */
public class LimitedBlockingQueueTest {

    @Test
    public void test1() {
        System.out.println("===== test1 =====");

        // 获取运行状态
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("getQueueSize: " + MusicUtils.executor.getQueue().size());
                        System.out.println("getPoolSize: " + MusicUtils.executor.getPoolSize());
                        System.out.println("getActiveCount: " + MusicUtils.executor.getActiveCount());
                        System.out.println("getTaskCount: " + MusicUtils.executor.getTaskCount());
                        System.out.println("getCompletedTaskCount: " + MusicUtils.executor.getCompletedTaskCount());
                        System.out.println("getMaximumPoolSize: " + MusicUtils.executor.getMaximumPoolSize());
                        System.out.println("getLargestPoolSize: " + MusicUtils.executor.getLargestPoolSize());
                        TimeUnit.SECONDS.sleep(time(3));
                    } catch (Throwable ignore) {
                    }
                }
            }
        }).start();

        for (int i = 0; i < 20; i++) {
            MusicUtils.executor.execute(new Runnable() {
                @Override
                public void run() {
                    task(Thread.currentThread().getName());
                }
            });
        }

        System.out.println("任务完成 ...... ");

        // 增加下载完成后关闭线程池
        while (true) {
            try {
                if (MusicUtils.executor.getActiveCount() <= 0 && !MusicUtils.executor.isShutdown()) {
                    System.out.println("executor shutdown...");
                    MusicUtils.executor.shutdown();
                    break;
                }
                TimeUnit.SECONDS.sleep(time(3));
            } catch (Throwable ignore) {
            }
        }
    }

    /**
     * 需要运行的任务
     *
     * @param name 任务参数, 根据需要自定义
     */
    private void task(String name) {
        try {
            System.out.println("current thread name: " + name);
            TimeUnit.SECONDS.sleep(time(10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 随机睡眠时间 [1, length] */
    private int time(int length) {
        Random random = new Random();
        // [1, length]
        return random.nextInt(length) + 1;
    }

}