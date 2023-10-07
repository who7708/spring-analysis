package com.example.demo.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Java异步编程——深入源码分析FutureTask
 * https://cloud.tencent.com/developer/article/1480189
 *
 * @author Chris
 * @date 2020/05/19
 * @since 1.0.0
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> result = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (int i = 0; i < 200; i++) {
                    System.out.println("任务1异步执行：" + i);
                }
                return "任务1";
            }
        });



        Future<String> result2 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (int i = 0; i < 200; i++) {
                    System.out.println("任务2异步执行：" + i);
                }

                return "任务2";
            }
        });

        try {
            // 结果获取
            System.out.println(result.get() + " : 执行完成");
            System.out.println(result2.get() + " : 执行完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池关闭
            executorService.shutdown();
        }
    }
}
