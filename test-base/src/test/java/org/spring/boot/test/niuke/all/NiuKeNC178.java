package org.spring.boot.test.niuke.all;

import org.junit.Test;
import org.spring.boot.test.niuke.NiuKeUtils;
import org.spring.boot.test.niuke.TreeNode;

/**
 * NC178 打家劫舍，二叉树
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC178 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        // 5
        // int[] arr = {2, 1, 2, 0, 2, 0, 1};
        // 12
        // int[] arr = {3,2,10};
        // 13
        int[] arr = {3, 5, 0, 10};
        TreeNode root = NiuKeUtils.createBinaryTreeWithoutZero(arr);
        {
            long begin = System.nanoTime();
            int robByRecursion = robByRecursion(root);
            System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
            System.out.println("robByRecursion = " + robByRecursion);
        }
        {
            long begin = System.nanoTime();
            int robByDp = robByDp(root);
            System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
            System.out.println("robByDp = " + robByDp);
        }
    }

    // 递归方式
    int robByRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //记录选择当前节点的可能最大金额
        int sum = root.val;
        if (root.left != null) {
            //左子节点不为空，则加上对应孙子的情况
            sum += robByRecursion(root.left.left) + robByRecursion(root.left.right);
        }
        if (root.right != null) {
            //右子节点不为空，则加上对应孙子的情况
            sum += robByRecursion(root.right.left) + robByRecursion(root.right.right);
        }
        //返回选与不选两种情况中的较大者
        return Math.max(sum, robByRecursion(root.left) + robByRecursion(root.right));
    }

    public int robByDp(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // dp[0]表示选择当前节点的最大金额，dp[1]表示不选择当前节点的最大金额
        int[] dp = dfs(root);
        return Math.max(dp[0], dp[1]);
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        // 左子节点情况
        int[] left = dfs(root.left);
        // 右子节点情况
        int[] right = dfs(root.right);
        // 当前节点的情况
        int[] dp = new int[2];
        // 选择当前节点
        dp[0] = root.val + left[1] + right[1];
        // 不选择当前节点
        dp[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return dp;
    }

}
