package com.example.demo.reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * https://juejin.cn/post/6844904085091516430
 *
 * @author Chris
 * @date 2020/04/21 23:34
 * @since 1.0.0
 */
public class TestSoftReference {
    // -Xmx20m -XX:+PrintGCDetails
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
        // m = null;
        System.out.println(m.get());
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        // 再分配一个数组， heap 将装不下，这时候系统会进行gc，如果不够，将把软引用指向的对象回收掉
        byte[] b = new byte[1024 * 1024 * 15];
        System.out.println(m.get());
    }
}
