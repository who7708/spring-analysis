package org.spring.boot.test.niuke.all;

import org.junit.Test;

/**
 * 连续子数组的最大和
 *
 *
 * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。求所有子数组的和的最大值。
 * 数据范围:
 * 输入：
 * [1,-2,3,10,-4,7,2,-5]
 * 返回值： 18
 * 说明：
 * 经分析可知，输入数组的子数组[3,10,-4,7,2]可以求得最大和为18
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC19 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here

        NiuKeNC19 niuKeNC19 = new NiuKeNC19();

        int[] arr = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println("niuKeNC19.FindGreatestSumOfSubArray(arr) = " + niuKeNC19.FindGreatestSumOfSubArray(arr));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param array int整型一维数组
     * @return int整型
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        // write code here
        // 方法一：空间复杂度O(n)
        // dp[i]表示以num[i]结尾的数组最大和
        // int[] dp = new int[array.length];
        // dp[0] = array[0];
        // int max = array[0];
        // for (int i = 1; i < array.length; i++) {
        //     if (dp[i - 1] > 0) {
        //         dp[i] = dp[i - 1] + array[i];
        //     } else dp[i] = array[i];
        //     max = dp[i] > max ? dp[i] : max;
        // }
        // return max;

        // 方法二：空间复杂度O(1)
        // 记录子数组和
        int sum = array[0];
        // 记录最大子数组和
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            // // 原子数组和为正，则原子数组与当前元素组成的子数组和大于以当前元素
            // // 单独构成的子数组
            // if (sum > 0) {
            //     sum = sum + array[i];
            // }
            // // 原子数组和不为正，则原子数组与当前元素组成的子数组和小于以当前元素
            // // 单独构成的子数组，则以当前元素作为新的最大子数组
            // else {
            //     sum = array[i];
            // }
            // 优化动态规划，确定sum的最大值
            sum = Math.max(sum + array[i], array[i]);
            // 记录最大值
            res = Math.max(sum, res);
        }
        return res;
    }
}
