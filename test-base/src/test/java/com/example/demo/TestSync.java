package com.example.demo;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/04/12
 */
public class TestSync {
    private static final Object obj = new Object();

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println(obj.hashCode());
        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}
