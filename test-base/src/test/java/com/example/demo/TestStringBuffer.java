package com.example.demo;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/04/13
 */
public class TestStringBuffer {
    public static void main(String[] args) {
        add("aaa", "bbb");
    }

    // 锁消除
    public static void add(String str1, String str2) {
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2);
    }

    // 锁粗化
    public static String test(String str) {
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while (i < 100) {
            sb.append(str);
            i++;
        }
        return sb.toString();
    }
}
