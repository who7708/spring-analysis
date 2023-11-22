package org.spring.boot.test.niuke.all;

import org.junit.Test;

/**
 * NC35 编辑距离(二)
 * 给定两个字符串str1和str2，再给定三个整数ic，dc和rc，分别代表插入、删除和替换一个字符的代价，请输出将str1编辑成str2的最小代价。
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC35 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        final String str1 = "abc";
        String str2 = "adc";
        int ic = 1;
        int dc = 1;
        int rc = 1;
        {
            long begin = System.nanoTime();
            int minEditCost = minEditCost(str1, str2, ic, dc, rc);
            System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
            System.out.println("minEditCost = " + minEditCost);
        }

        {
            long begin = System.nanoTime();
            int minDistance = minDistance(str1, str2);
            System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
            System.out.println("minDistance = " + minDistance);
        }
    }

    // 作者：力扣官方题解
    // 链接：https://leetcode.cn/problems/edit-distance/solutions/188223/bian-ji-ju-chi-by-leetcode-solution/
    // 来源：力扣（LeetCode）
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    /**
     * min edit cost
     *
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @param ic   int整型 insert cost
     * @param dc   int整型 delete cost
     * @param rc   int整型 replace cost
     * @return int整型
     */
    //"abc","adc",5,3,100
    public int minEditCost(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null) {
            if (str2 == null) {
                return 0;
            }
            return str2.length() * ic;
        }
        if (str2 == null) {
            return str1.length() * ic;
        }
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        //f[i][j]表示将str1的前i个字符编辑成str2的前j个字符 所花费的最小成本
        int[][] f = new int[arr1.length + 1][arr2.length + 1];
        for (int i = 0; i <= arr1.length; i++) {
            for (int j = 0; j <= arr2.length; j++) {
                if (i == 0) {
                    f[i][j] = ic * j;
                    continue;
                }
                if (j == 0) {
                    f[i][j] = dc * i;
                    continue;
                }
                //增加,删除
                f[i][j] = Math.min(f[i][j - 1] + ic, f[i - 1][j] + dc);
                //替换
                if (arr1[i - 1] == arr2[j - 1]) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                } else {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + rc);
                }
            }
        }
        return f[arr1.length][arr2.length];
    }
}
