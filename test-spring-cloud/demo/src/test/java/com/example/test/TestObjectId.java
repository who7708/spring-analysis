package com.example.test;

import com.example.id.ObjectId;
import org.junit.Test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/13
 */
public class TestObjectId {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        // 6579c53302a70a5ec49bc1df
        // 4 字节的时间戳 + 3 个字节机器标识码 + 2 个字节进程号 + 3 个字节自增数
        printObjectId();
    }

    private static void printObjectId() {
        ObjectId objectId = ObjectId.get();
        String objString = objectId.toString();
        System.out.println("objString = " + objString);
        // String hexString = objectId.toHexString();
        // System.out.println("hexString = " + hexString);
    }

    @Test
    public void test2() throws Exception {
        System.out.println("===== test2 =====");
        for (int i = 0; i < 1000; i++) {
            printObjectId();
        }

        ObjectId objectId = new ObjectId("632c6d93d65f74baeb22a2c9");
        System.out.println(objectId);
    }
}
