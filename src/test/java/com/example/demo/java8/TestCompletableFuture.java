package com.example.demo.java8;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

/**
 * @author Chris
 * @date 2020/04/24
 * @since 1.0.0
 */
public class TestCompletableFuture {

    @Test
    public void test1() throws InterruptedException {
        System.out.println("===== test1 =====");

        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(new Supplier<Double>() {
            @Override
            public Double get() {
                return fetchPrice();
            }
        });
        // 如果执行成功:
        cf.thenAccept(new Consumer<Double>() {
            @Override
            public void accept(Double result) {
                System.out.println("price: " + result);
            }
        });
        // 如果执行异常:
        cf.exceptionally(new Function<Throwable, Double>() {
            @Override
            public Double apply(Throwable e) {
                e.printStackTrace();
                return null;
            }
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    @Test
    public void test2() throws InterruptedException {
        System.out.println("===== test2 =====");
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return queryCode("中国石油");
            }
        });
        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync(new Function<String, Double>() {
            @Override
            public Double apply(String code) {
                return fetchPrice(code);
            }
        });
        // cfFetch成功后打印结果:
        cfFetch.thenAccept(new Consumer<Double>() {
            @Override
            public void accept(Double result) {
                System.out.println("price: " + result);
            }
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    @Test
    public void test3() throws InterruptedException {
        System.out.println("===== test3 =====");
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> queryCode("中国石油", "https://finance.sina.com.cn/code/"));
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> queryCode("中国石油", "https://money.163.com/code/"));

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> fetchPrice((String) code, "https://finance.sina.com.cn/price/"));
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> fetchPrice((String) code, "https://money.163.com/price/"));

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);

        // 最终结果:
        cfFetch.thenAccept((result) -> System.out.println("price: " + result));
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}
// CompletableFuture可以指定异步处理流程：
//
// thenAccept()处理正常结果；
// exceptional()处理异常结果；
// thenApplyAsync()用于串行化另一个CompletableFuture；
// anyOf()和allOf()用于并行化多个CompletableFuture。