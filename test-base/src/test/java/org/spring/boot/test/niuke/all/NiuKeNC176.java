package org.spring.boot.test.niuke.all;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * NC176 打家劫舍，非环形
 * <a href="https://www.nowcoder.com/practice/c5fbf7325fbd4c0ea3d0c3ea6bc6cc79">...</a>
 * 你是一个经验丰富的小偷，准备偷沿街的一排房间，每个房间都存有一定的现金，为了防止被发现，你不能偷相邻的两家，
 * 即，如果偷了第一家，就不能再偷第二家；如果偷了第二家，那么就不能偷第一家和第三家。
 * 给定一个整数数组nums，数组中的元素表示每个房间存有的现金数额，请你计算在不被发现的前提下最多的偷窃金额。
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/21
 */
public class NiuKeNC176 {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        // 12
        // int[] nums = {2, 7, 9, 3, 1};
        // 4
        // int[] nums = {1, 2, 3, 1};

        int[] nums = new int[500];
        for (int i = 0; i < 500; i++) {
            int random = RandomUtils.nextInt(1, 1000);
            nums[i] = random;
        }
        System.out.println("nums = " + Arrays.toString(nums));

        {
            long begin = System.nanoTime();
            long robByRecursion = robByRecursion(nums);
            System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
            System.out.println("robByRecursion = " + robByRecursion);
        }
        {
            long begin = System.nanoTime();
            long robByDp = robByDp(nums);
            System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
            System.out.println("robByDp = " + robByDp);
        }
        {
            long begin = System.nanoTime();
            long robByDp2 = robByDp2(nums);
            System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
            System.out.println("robByDp2 = " + robByDp2);
        }
    }

    // 0-k 号房中能拿到的最多金额
    public int process(int[] nums, int k) {
        if (k == 0) {
            return nums[0];
        }
        if (k == 1) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(process(nums, k - 2) + nums[k], process(nums, k - 1));
    }

    // 记录化搜索优化，即将k的结果缓存起来
    // 0-k 号房中能拿到的最多金额
    public int process(int[] nums, int k, Map<Integer, Integer> cacheMap) {
        if (k == 0) {
            return nums[0];
        }
        if (k == 1) {
            return Math.max(nums[0], nums[1]);
        }
        if (cacheMap.containsKey(k)) {
            return cacheMap.get(k);
        }

        int res = Math.max(
                process(nums, k - 2, cacheMap) + nums[k],
                process(nums, k - 1, cacheMap));
        cacheMap.put(k, res);
        return res;
    }

    // 两种递归方式（自顶向下）：同样 500 个数
    public int robByRecursion(int[] nums) {
        // 非常耗时
        // return process(nums, nums.length - 1);
        // 1ms内即可算出结果
        return process(nums, nums.length - 1, new HashMap<>());
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
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[n];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    // 动态规划（自底向上）优化空间
    // 时间复杂度： O(n)
    // 空间复杂度： O(1)
    public int robByDp2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int dp0 = nums[0];
        int dp1 = Math.max(nums[0], nums[1]);
        int r = 0;

        for (int i = 2; i < n; i++) {
            r = Math.max(dp1, dp0 + nums[i]);
            dp0 = dp1;
            dp1 = r;
        }
        return r;
    }

}
