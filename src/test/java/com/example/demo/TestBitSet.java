package com.example.demo;

import org.apache.commons.lang3.StringUtils;

import java.util.BitSet;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/04/13
 */
public class TestBitSet {
    public static void main(String[] args) {
        BitSet set1 = new BitSet();

        // for (int i = 0; i < 100; i++) {
        //     // String a = Integer.toBinaryString(i & 31);
        //     String a = Integer.toBinaryString(i & 0x1f);
        //     // 计算 1 向右移动多少位
        //     String b = Integer.toBinaryString(1 << (i & 0x1f));
        //     if (i < 10) {
        //         System.out.println("0" + i + " : " + StringUtils.leftPad(a, 32, '0') + " : " + StringUtils.leftPad(b, 32, '0'));
        //     } else {
        //         System.out.println(i + " : " + StringUtils.leftPad(a, 32, '0') + " : " + StringUtils.leftPad(b, 32, '0'));
        //     }
        // }

        testBitmap(10000000);
    }

    /**
     * 存储
     *
     * @param n n个数
     */
    private static void testBitmap(int n) {
        int[] a = new int[1 + n / 32];
        for (int i = 0; i < n; i++) {
            int index = i >> 5;
            a[index] |= (1 << (i & 0x1F));
        }

        for (int i = 0; i < a.length; i++) {
            System.out.println(i + " : " + StringUtils.leftPad(Integer.toBinaryString(a[i]), 32, '0'));
        }
    }
}
