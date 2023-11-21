package org.spring.boot.test.niuke.all;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * NC177 打家劫舍，有环形
 * <a href="https://www.nowcoder.com/practice/a5c127769dd74a63ada7bff37d9c5815">...</a>
 * 你是一个经验丰富的小偷，准备偷沿街的一排房间，每个房间都存有一定的现金，为了防止被发现，你不能偷相邻的两家，
 * 即，如果偷了第一家，就不能再偷第二家；如果偷了第二家，那么就不能偷第一家和第三家。
 * 给定一个整数数组nums，数组中的元素表示每个房间存有的现金数额，请你计算在不被发现的前提下最多的偷窃金额。
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/21
 */
public class NiuKeNC177 {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        // 6
        // int[] nums = {1,2,3,4};
        // 6
        int[] nums = {1, 3, 6};

        // int[] nums = new int[500];
        // for (int i = 0; i < 500; i++) {
        //     int random = RandomUtils.nextInt(1, 1000);
        //     nums[i] = random;
        // }
        System.out.println("nums = " + Arrays.toString(nums));

        {
            long begin = System.nanoTime();
            long robByDp = robByDp(nums);
            System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
            System.out.println("robByDp = " + robByDp);
        }
        // {
        //     long begin = System.nanoTime();
        //     long robByDp2 = robByDp2(nums);
        //     System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
        //     System.out.println("robByDp2 = " + robByDp2);
        // }
    }

    // 动态规划（自底向上）空间换时间
    // 时间复杂度： O(n)
    // 空间复杂度： O(n)
    // dp[i] 从第 0 号房间到第 i 号房间能拿到的最大金额
    // 题目要求的最终答案： dp[n-1]
    // i = 0: dp[i] = nums[i]
    // i = 1: dp[i] = max(num[i], num[i - 1])
    // i > 1: dp[i] = max(dp[i-1], dp[i-2] + nums[i])
    public int robByDp(int[] nums) {
        // dp[i]表示长度为i的数组，最多能偷取多少钱
        int[] dp = new int[nums.length + 1];
        // 选择偷了第一家
        dp[1] = nums[0];
        // 最后一家不能偷
        for (int i = 2; i < nums.length; i++) {
            // 对于每家可以选择偷或者不偷
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        }
        int res = dp[nums.length - 1];
        // 清除dp数组，第二次循环
        Arrays.fill(dp, 0);
        // 不偷第一家，也就是从第二家开始
        dp[2] = nums[1];
        // 可以偷最后一家
        for (int i = 2; i <= nums.length; i++) {
            // 对于每家可以选择偷或者不偷
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        }
        // 选择最大值
        return Math.max(res, dp[nums.length]);
    }

    // // 动态规划（自底向上）优化空间
    // // 时间复杂度： O(n)
    // // 空间复杂度： O(1)
    // public int robByDp2(int[] nums) {
    //     int n = nums.length;
    //     if (n == 1) {
    //         return nums[0];
    //     }
    //     if (n == 2) {
    //         return Math.max(nums[0], nums[1]);
    //     }
    //     // 选择偷了第一家
    //     int dp0 = nums[0];
    //     int dp1 = Math.max(nums[0], nums[1]);
    //     int r1 = 0;
    //     // 最后一家不能偷, < n
    //     for (int i = 2; i < n; i++) {
    //         r1 = Math.max(dp1, dp0 + nums[i - 1]);
    //         dp0 = dp1;
    //         dp1 = r1;
    //     }
    //     // 选择不偷第一家
    //     dp0 = nums[1];
    //     dp1 = Math.max(nums[1], nums[2]);
    //     int r2 = 0;
    //     // 最后一家能偷, <= n
    //     for (int i = 2; i <= n; i++) {
    //         r2 = Math.max(dp1, dp0 + nums[i - 1]);
    //         dp0 = dp1;
    //         dp1 = r2;
    //     }
    //     return Math.max(r1, r2);
    // }

}
