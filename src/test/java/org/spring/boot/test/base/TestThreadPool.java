package org.spring.boot.test.base;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-22
 */
public class TestThreadPool {
    @Test
    public void test1() {
        System.out.println("===== test1 =====");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // 核心线程数
                3, // 最大线程数
                2, // 线程存活时间
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1), // 阻塞队列
                Executors.defaultThreadFactory(), // 线程工厂
                /**
                 * 四种拒绝策略：
                 * 1. AbortPolicy：线程满了，如果仍有线程需要处理，则直接抛出异常
                 * 2. CallerRunsPolicy：
                 * 3. DiscardPolicy：线程满了，直接丢弃，不抛出异常
                 * 4. DiscardOldestPolicy：线程满了，直接丢弃，最先进入线程池的线程，不抛出异常
                 */
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 3; i++) {
            executor.execute(()->{
                System.out.println();
            });
        }

    }
}
