package org.spring.boot.test.niuke.mt52007812;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 6.
// 小美的数组构造
// 小美拿到了一个数组 a，她准备构造一个数组 b满足：
// 1. b的每一位都和 a对应位置不同，即 bi ≠ ai
// 2. b的所有元素之和都和 a相同。
// 3. b的数组均为正整数。
// 请你告诉小美有多少种构造方式。由于答案过大，请对 1e9 + 7 取模。
// 输入例子：
// 3
// 1 1 3
// 输出例子：
// 1

// 输入例子：
// 3
// 1 1 1
// 输出例子：
// 0
public class TestNiuKeMain6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            list.add(k);
            sum += k;
        }
        System.out.println(pro(list, 0, sum));
    }

    //动态规划，确定变量为下标和余值，建立二维dp数组
    private static int pro(List<Integer> list, int index, int sum) {
        int n = list.size();
        int[][] dp = new int[n][sum + 1];
        for (int i = 1; i <= sum; i++) {//确定最后一行，即最后一位数时的结果，然后往前推dp[index][sum]
            if (i != list.get(n - 1)) {
                dp[n - 1][i] = 1;
            } else {
                dp[n - 1][i] = 0;
            }
        }

        for (int row = n - 2; row >= 0; row--) {
            for (int col = 1; col <= sum; col++) {
                int t = 1;
                int count = 0;
                while (col >= n - row && col - t >= n - row - 1) {
                    if (t != list.get(row)) {
                        count = (count + dp[row + 1][col - t]) % (int) (1e9 + 7);
                    }
                    t++;
                }
                dp[row][col] = count;
            }
        }

        return dp[index][sum];
    }
}