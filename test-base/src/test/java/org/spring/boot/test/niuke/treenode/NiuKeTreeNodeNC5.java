package org.spring.boot.test.niuke.treenode;

import org.spring.boot.test.niuke.NiuKeUtils;
import org.spring.boot.test.niuke.TreeNode;

/**
 * NC5 二叉树根节点到叶子节点的所有路径和
 * <a href="https://www.nowcoder.com/practice/185a87cd29eb42049132aed873273e83?tpId=196&tqId=37049&ru=/exam/oj">...</a>
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeTreeNodeNC5 {

    public static void main(String[] args) {
        // int[] arr = {1, 2, 3}; // 25
        // int[] arr = {1, 0}; // 10
        // int[] arr = {1, 2, 0, 3, 4};// 257
        // int[] arr = {1, 2, 0, 3, 4, 8, 7, 9, 6, 5}; // 3935
        int[] arr = {1, 2, 0, 3, 4, 8, 7, 9};// 1578
        TreeNode root = NiuKeUtils.createBinaryTree(arr);
        int sum = sumNumbers(root);
        System.out.println(sum);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param root TreeNode类
     * @return int整型
     */
    private static int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return preorderSumNumbers(root, 0);
    }

    private static int preorderSumNumbers(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        sum = sum * 10 + node.val;
        if (node.left == null && node.right == null) {
            return sum;
        }
        return preorderSumNumbers(node.left, sum) + preorderSumNumbers(node.right, sum);
    }

}
