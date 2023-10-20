package org.spring.boot.test.niuke.mt52007812;

import java.util.Arrays;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 5.
// 小美的数组操作2
// 小美拿到了一个数组，她每次可以进行如下操作：
// 选择两个元素，一个加 1，另一个减 1。
// 小美总共进行了
// k次操作。她希望你回答最终数组是否是非降序，你能帮帮她吗？
// 请注意，元素可能会被减成负数！
public class TestNiuKeMain5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 询问次数
        int t = in.nextInt();
        // 进行t次数询问
        for (int i = 0; i < t; i++) {
            // 数组个数
            int n = in.nextInt();
            // 对数组操作次数
            int k = in.nextInt();

            // 初始化数组
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = in.nextInt();
            }

            // 操作k次
            for (int p = 0; p < k; p++) {
                // 操作数组的两个元素
                int uIdx = in.nextInt() - 1;
                int vIdx = in.nextInt() - 1;
                nums[uIdx] = ++nums[uIdx];
                nums[vIdx] = --nums[vIdx];
            }
            // System.out.println(Arrays.toString(nums));
            // 判断是否为降序
            int count = 0;
            for (int m = 1; m < n; m++) {
                if (nums[m - 1] <= nums[m]) {
                    count++;
                }
            }
            if (count == (n - 1)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}