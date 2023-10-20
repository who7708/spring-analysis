package com.example.demo;

/**
 * https://zhuanlan.zhihu.com/p/135462276
 *
 * @author Chris
 * @date 2020/04/21 22:16
 * @since 1.0.0
 */
public class TestCacheLInePadding02 {
    private static class Padding {
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    private static class T extends Padding {
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < 1000_0000L; i++) {
                    arr[0].x = i;
                }
            }
        }, "ThreadA");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < 1000_0000L; i++) {
                    arr[1].x = i;
                }
            }
        }, "ThreadB");

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start) / 100_0000);


        cacheLine();
    }

    private static void cacheLine() {

        long[] arr = new long[64 * 1024 * 1024];
        long start = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 3;
        }
        System.out.println((System.nanoTime() - start) / 100_0000);

        long start2 = System.nanoTime();
        for (int i = 0; i < arr.length; i += 8) {
            arr[i] *= 3;
        }
        System.out.println((System.nanoTime() - start2) / 100_0000);

        // 表面上看，循环二工作量为第循环一的1/8；但是执行时间是相差不大的，因为每 8个Long占用8*8=64字节，
        // 正好一个cache，也就是说这两个循环访问内存的次数是一致的，导致耗时相差不大(访问cache时间rt忽略不计)
    }
}
