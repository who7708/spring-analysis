package org.spring.boot.test.io;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerSocketServerPool {
    // 1.创建一个线程池的成员变量用于存储一个线程池对象
    private final ExecutorService executorService;

    /**
     * 创建这个类的对象的时候需要初始化线程池对象
     *
     * @param maxThreadNum
     * @param queueSize
     */
    public HandlerSocketServerPool(int maxThreadNum, int queueSize) {
        executorService = new ThreadPoolExecutor(3, maxThreadNum, 120, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    /**
     * 提供一个方法来提交任务到线程池的任务队列来暂存，等着线程执行
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
