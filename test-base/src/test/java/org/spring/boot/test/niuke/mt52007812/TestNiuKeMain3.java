package org.spring.boot.test.niuke.mt52007812;

import java.util.Scanner;

// 3.
// 小美的01串翻转
// 小美定义一个 01 串的权值为：每次操作选择一位取反，使得相邻字符都不相等的最小操作次数。
// 例如，"10001"的权值是 1，因为只需要修改一次：对第三个字符取反即可。
// 现在小美拿到了一个 01 串，她希望你求出所有非空连续子串的权值之和，你能帮帮她吗？
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class TestNiuKeMain3 {

    // 作者：cx_333
    // 链接：https://www.nowcoder.com/exam/test/74676421/submission?pid=52007812
    // 来源：牛客网
    // 001010001000101101001011110011111110100101010110
    // 7880
    public static void main(String[] args) {
        System.out.println("===== test1 =====");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        if (s.isEmpty() || s.length() > 2000) {
            System.out.println("输入的01串错误");
            return;
        }

        System.out.println(s);
        int len = s.length();
        String s1 = "";
        String s2 = "";

        for (int i = 0; i <= len / 2; ++i) {
            s1 += '1';
            s1 += '0';
            s2 += '0';
            s2 += '1';
        }

        int res = 0;
        int[][] nums = new int[len - 1][2];
        char[] c = s.toCharArray();
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for (int i = 0; i < len - 1; ++i) {
            for (int j = i; j < len; ++j) {
                if (c[j] != c1[j]) {
                    nums[i][0]++;
                }
                if (c[j] != c2[j]) {
                    nums[i][1]++;
                }
                int min = Math.min(nums[i][0], nums[i][1]);
                res += min;
            }
        }
        System.out.println(res);

        System.out.println("finished...");
    }
}