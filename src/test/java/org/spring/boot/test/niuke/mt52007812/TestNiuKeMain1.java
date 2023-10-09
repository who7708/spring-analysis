package org.spring.boot.test.niuke.mt52007812;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 1.
// 小美的加法
// 小美有一个长度为 n 的数组，她想将这个数组进行求和，即 sum = a1 + a2 +...+ an
// 小美可以使用一次魔法（也可以不使用），将其中一个加号变成乘号，使得 sum 最大。
// 求出最大的 sum 。
public class TestNiuKeMain1 {
    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        System.out.println("===== test1 =====");
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        System.out.println(count);
        long sum = 0;
        long[] nums = new long[count];
        for (int i = 0; i < count; i++) {
            nums[i] = in.nextInt();
            sum += nums[i];
        }
        long res = 0;
        for (int i = 1; i < count; i++) {
            res = Math.max(res, sum - nums[i] - nums[i - 1] + nums[i] * nums[i - 1]);
        }
        System.out.println(res);
    }
}