package org.spring.boot.test.niuke.all;

import org.junit.Test;

/**
 * NC59 矩阵的最小路径和，无障碍
 * <a href="https://www.nowcoder.com/practice/7d21b6be4c6b429bb92d219341c4f8bb">...</a>
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC59 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        // final int[][] matrix = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        final int[][] matrix = {{1, 2, 3}, {1, 2, 3}};
        long begin = System.nanoTime();
        int minPathSum = minPathSum(matrix);
        System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
        System.out.println("minPathSum = " + minPathSum);
    }

    /**
     * 解题思路：
     * 此题是典型的动态规划题目。
     *
     * 状态定义：
     *
     * 设 dpdpdp 为大小 m×nm \times nm×n 矩阵，其中 dp[i][j]dp[i][j]dp[i][j] 的值代表直到走到 (i,j)(i,j)(i,j) 的最小路径和。
     * 转移方程：
     *
     * 题目要求，只能向右或向下走，换句话说，当前单元格 (i,j)(i,j)(i,j) 只能从左方单元格 (i−1,j)(i-1,j)(i−1,j) 或上方单元格 (i,j−1)(i,j-1)(i,j−1)
     * 走到，因此只需要考虑矩阵左边界和上边界。
     *
     * 走到当前单元格 (i,j)(i,j)(i,j) 的最小路径和 === “从左方单元格 (i−1,j)(i-1,j)(i−1,j) 与 从上方单元格 (i,j−1)(i,j-1)(i,j−1) 走来的 两个最小路径和中较小的
     * ”
     * +++ 当前单元格值 grid[i][j]grid[i][j]grid[i][j] 。具体分为以下 444 种情况：
     * 当左边和上边都不是矩阵边界时： 即当i≠0i \not= 0i
     * 
     * =0, j≠0j \not= 0j
     * 
     * =0时，dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) +
     * grid[i][j]dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j] ；
     * 当只有左边是矩阵边界时： 只能从上面来，即当i=0,j≠0i = 0, j \not= 0i=0,j
     * 
     * =0时， dp[i][j]=dp[i][j−1]+grid[i][j]dp[i][j] = dp[i][j - 1] + grid[i][j]dp[i][j]=dp[i][j−1]+grid[i][j] ；
     * 当只有上边是矩阵边界时： 只能从左面来，即当i≠0,j=0i \not= 0, j = 0i
     * 
     * =0,j=0时， dp[i][j]=dp[i−1][j]+grid[i][j]dp[i][j] = dp[i - 1][j] + grid[i][j]dp[i][j]=dp[i−1][j]+grid[i][j] ；
     * 当左边和上边都是矩阵边界时： 即当i=0,j=0i = 0, j = 0i=0,j=0时，其实就是起点， dp[i][j]=grid[i][j]dp[i][j] =
     * grid[i][j]dp[i][j]=grid[i][j]；
     * 初始状态：
     *
     * dpdpdp 初始化即可，不需要修改初始 000 值。
     * 返回值：
     *
     * 返回 dpdpdp 矩阵右下角值，即走到终点的最小路径和。
     * 其实我们完全不需要建立 dpdpdp 矩阵浪费额外空间，直接遍历 grid[i][j]grid[i][j]grid[i][j] 修改即可。这是因为：grid[i][j] = min(grid[i - 1][j],
     * grid[i][j - 1]) + grid[i][j] ；原 gridgridgrid 矩阵元素中被覆盖为 dpdpdp 元素后（都处于当前遍历点的左上方），不会再被使用到。
     *
     * 复杂度分析：
     * 时间复杂度 O(M×N)O(M \times N)O(M×N) ： 遍历整个 gridgridgrid 矩阵元素。
     * 空间复杂度 O(1)O(1)O(1) ： 直接修改原矩阵，不使用额外空间。
     *
     * 作者：Krahets
     * 链接：<a
     * href="https://leetcode.cn/problems/minimum-path-sum/solutions/25943/zui-xiao-lu-jing-he-dong-tai-gui-hua-gui-fan-liu-c/">...</a>
     * 来源：力扣（LeetCode）
     *
     * <a
     * href="ttps://uploadfiles.nowcoder.com/images/20220205/397721558_1644044733286/47BEDBEA9F525B5752D6DB1BE8B0C928">...</a>
     *
     * <a
     * href="https://pic.leetcode-cn.com/c32cf8caeabc08a2a759bb0eff310cfa3a424617e3b2f342d18a4ce6e1b450c8-Picture1.png">...</a>
     *
     * @param matrix int整型二维数组 the matrix
     * @return int整型
     */
    public int minPathSum(int[][] matrix) {
        // write code here
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    matrix[i][j] += matrix[i][j - 1];
                } else if (j == 0) {
                    matrix[i][j] += matrix[i - 1][j];
                } else {
                    matrix[i][j] += Math.min(matrix[i][j - 1], matrix[i - 1][j]);
                }
            }
        }
        return matrix[matrix.length - 1][matrix[0].length - 1];
    }
}
