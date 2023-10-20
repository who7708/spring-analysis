package com.example.demo.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @date 2020/05/13
 * @since 1.0.0
 */
public class TestPhantomReference {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        final PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    LIST.add(new byte[1024 * 1024]);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    System.out.println(phantomReference.get());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Reference<? extends M> poll = QUEUE.poll();
                    if (poll != null) {
                        System.out.println("--- 虚引用对象被jvm回收了 ---" + poll);
                    }
                }
            }
        }).start();
    }
}
