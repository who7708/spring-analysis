package com.example.demo;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.spring.model.Student;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/04/12
 */
public class TestObject {

    private static ThreadLocal<String> test = new ThreadLocal<>();

    @Test
    public void test3() throws InterruptedException {
        System.out.println("===== test3 ====");
        test.set("hello world");
        System.out.println(test.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.set("hello world 2");
            }
        }).start();

        Thread.currentThread().join();
    }

    @Test
    public void test2() {
        System.out.println("===== test2 ====");
        AtomicInteger test = new AtomicInteger(0);
        int i = test.incrementAndGet();
        System.out.println(i);
    }

    /**
     * 内存布局测试
     * -XX:+PrintCommandLineFlags
     * -XX:+UseCompressedClassPointers java8 以上才有，是否开启 klass pointer 指针压缩。不压缩是 8 个字节，压缩是 4个字节
     * -XX:+UseCompressedOops java7以上有，是否开启普通对象指针压缩，由 8个字节压缩为 4 个字节
     */
    @Test
    public void test1() {
        System.out.println("===== test1 ====");
        Object obj = new Object();
        String layout1 = ClassLayout.parseInstance(obj).toPrintable();
        System.out.println(layout1);
        // java.lang.Object object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        // 前面两个是 markword
        //       8     4        (object header)                           7f 05 84 df (01111111 00000101 10000100 11011111) (-544995969)
        // klass pointer 开启压缩仅有4个字节
        //      12     4        (loss due to the next object alignment)
        // 由于没有成员变量，已占用字节数不足以被8整除，因此增加了4个字节用来进行字节补齐
        //
        // Instance size: 16 bytes
        // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        synchronized (obj) {
            Student student = Student.builder()
                    .id(101)
                    .name("helloworld")
                    .address("Shanghai")
                    .build();
            String layout2 = ClassLayout.parseInstance(student).toPrintable();
            System.out.println(layout2);

            GraphLayout graphLayout = GraphLayout.parseInstance(student);
            System.out.println(graphLayout.toPrintable());
        }
    }

    /**
     * 内存布局测试
     */
    @Test
    public void test4() {
        System.out.println("===== test1 ====");
        Object obj = new Object();
        String layout1 = ClassLayout.parseInstance(obj).toPrintable();
        System.out.println(layout1);
        synchronized (obj) {
            String layout2 = ClassLayout.parseInstance(obj).toPrintable();
            System.out.println(layout2);
            synchronized (obj) {
                String layout3 = ClassLayout.parseInstance(obj).toPrintable();
                System.out.println(layout3);
            }
        }
    }
}
