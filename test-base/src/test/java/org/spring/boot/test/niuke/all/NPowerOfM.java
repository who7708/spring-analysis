package org.spring.boot.test.niuke.all;

import org.junit.Test;

/**
 * m 的 n 次幂
 * <a href="https://leetcode.cn/problems/powx-n/description/">...</a>
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NPowerOfM {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        long begin = System.nanoTime();
        double myPow = myPow(2.0D, 30);
        System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
        System.out.println("code = " + myPow);
    }

    public double myPow(double x, int n) {
        return (long) n >= 0 ? quickMul(x, n) : 1.0 / quickMul(x, -(long) n);
    }

    public double quickMul(double x, long n) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (n > 0) {
            if (n % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            n /= 2;
        }
        return ans;
    }
}
