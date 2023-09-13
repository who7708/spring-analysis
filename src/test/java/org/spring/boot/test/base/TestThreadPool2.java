package org.spring.boot.test.base;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-22
 */
public class TestThreadPool2 {
    @Test
    public void test1() throws Exception {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                test("abc", false);
            }
        });
        t.start();
        t.join();
        System.out.println("--gc后--");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test("def", true);
            }
        });
        t2.start();
        t2.join();
    }

    private static void test(String s, boolean isGC) {
        try {
            ThreadLocal<Object> threadLocal = new ThreadLocal<>();
            threadLocal.set(s);
            // 失去对threadLocal的强引用 ,help gc
            threadLocal = null;
            if (isGC) {
                System.gc();
            }
            Thread t = Thread.currentThread();
            Class<? extends Thread> clz = t.getClass();
            Field field = clz.getDeclaredField("threadLocals");
            field.setAccessible(true);
            Object threadLocalMap = field.get(t);
            Class<?> tlmClass = threadLocalMap.getClass();
            Field tableField = tlmClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] arr = (Object[]) tableField.get(threadLocalMap);
            for (Object o : arr) {
                if (o != null) {
                    Class<?> entryClass = o.getClass();
                    Field valueField = entryClass.getDeclaredField("value");
                    Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                    valueField.setAccessible(true);
                    referenceField.setAccessible(true);
                    System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
