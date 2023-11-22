package org.spring.boot.test.niuke.all;

import org.junit.Test;

/**
 * NC49 最长的括号子串
 * 描述
 * 给出一个长度为 n 的，仅包含字符 '(' 和 ')' 的字符串，计算最长的格式正确的括号子串的长度。
 *
 * 例1: 对于字符串 "(()" 来说，最长的格式正确的子串是 "()" ，长度为 2 .
 * 例2：对于字符串 ")()())" , 来说, 最长的格式正确的子串是 "()()" ，长度为 4 .
 * 要求时间复杂度 O(n) ,空间复杂度 O(n).
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC49 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        // 2
        // final String s = "(()";
        // 4
        // final String s = "(())";
        // 4
        // final String s = "())((())";
        // 6
        // final String s = "())((()))";
        final String s = "())((())((()))";
        long begin = System.nanoTime();
        int longestValidParentheses = longestValidParentheses(s);
        System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
        System.out.println("longestValidParentheses = " + longestValidParentheses);
    }

    public int longestValidParentheses(String s) {
        // return longestValidParenthesesByDp(s);
        return longestValidParenthesesByPointer(s);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param s string字符串
     * @return int整型
     */
    public int longestValidParenthesesByDp(String s) {
        // write code here
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    /**
     * 解题思路：
     * 利用两个计数器 left 和 right 。首先，从左到右遍历字符串，对于遇到的每个‘(’，我们增加 left 计数器，对于遇到的每个 ‘)’ ，增加 right 计数器。每当 left 计数器与 right
     * 计数器相等时，计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。当 right 计数器比 left 计数器大时，将 left 和 right 计数器同时变回 0
     * 这样的做法贪心地考虑了以当前字符下标结尾的有效括号长度，每次当右括号数量多于左括号数量的时候之前的字符都扔掉不再考虑，重新从下一个字符开始计算，但这样会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 (()
     * ，这种时候最长有效括号是求不出来的。
     * 解决的方法也很简单，我们只需要从右往左遍历用类似的方法计算即可，只是这个时候判断条件反了过来
     * 1、当 left 计数器比 right 计数器大时，将 left 和 right 计数器同时变回 0
     * 2、当 left 计数器与 right 计数器相等时，计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串
     *
     * @param s string字符串
     * @return int整型
     */
    int longestValidParenthesesByPointer(String s) {
        // write code here
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}
