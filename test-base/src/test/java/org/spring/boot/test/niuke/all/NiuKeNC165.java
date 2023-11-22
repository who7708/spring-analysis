package org.spring.boot.test.niuke.all;

import org.junit.Test;

/**
 * NC165 最长公共子序列(一)
 * <a href="https://www.nowcoder.com/practice/8cb175b803374e348a614e34b80ae191">...</a>
 * 描述
 * 给定两个字符串 s1 和 s2，长度为m和n 。求两个字符串最长公共子序列的长度。
 * 所谓子序列，指一个字符串删掉部分字符（也可以不删）形成的字符串。例如：字符串 "arcaea" 的子序列有 "ara" 、 "rcaa" 等。但 "car" 、 "aaae" 则不是它的子序列。
 * 所谓 s1 和 s2 的最长公共子序列，即一个最长的字符串，它既是 s1 的子序列，也是 s2 的子序列。
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC165 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        System.out.println(LCS("abcde", "bdcaaa"));
        System.out.println(LCS("abc", "xyz"));
    }

    /**
     * s1和s2最长公共子序列的长度
     *
     * @param s1 string字符串
     * @param s2 string字符串
     * @return int整型
     */
    public int LCS(String s1, String s2) {
        // write code here
        int mLength = s1.length();
        int nLength = s2.length();
        int[][] dp = new int[mLength + 1][nLength + 1];
        // char[] c1 = s1.toCharArray();
        // char[] c2 = s2.toCharArray();
        // for (int i = 0; i <= mLength; i++) {
        //     dp[i][0] = 0;
        // }
        // for (int j = 0; j <= nLength; j++) {
        //     dp[0][j] = 0;
        // }
        for (int i = 1; i <= mLength; i++) {
            for (int j = 1; j <= nLength; j++) {
                // if (c1[i - 1] != c2[j - 1]) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[mLength][nLength];
    }
}
