package org.spring.boot.test.niuke.all;

import org.junit.Test;
import org.spring.boot.test.niuke.NiuKeUtils;
import org.spring.boot.test.niuke.TreeNode;

/**
 * NC5 二叉树根节点到叶子节点的所有路径和
 * <a href="https://www.nowcoder.com/practice/185a87cd29eb42049132aed873273e83?tpId=196&tqId=37049&ru=/exam/oj">...</a>
 *
 * 描述
 * 给定一个二叉树的根节点root，该树的节点值都在数字
 * 0−9 之间，每一条从根节点到叶子节点的路径都可以用一个数字表示。
 * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
 * 2.叶子节点是指没有子节点的节点
 * 3.路径只能从父节点到子节点，不能从子节点到父节点
 * 4.总节点数目为n
 *
 * 例如根节点到叶子节点的一条路径是 1→2→3,那么这条路径就用 123 来代替。
 * 找出根节点到叶子节点的所有路径表示的数字之和
 * 例如：1->2->3 的二叉树
 * 这颗二叉树一共有两条路径，
 * 根节点到叶子节点的路径 1→2 用数字 12 代替
 * 根节点到叶子节点的路径 1→3 用数字 13 代替
 * 所以答案为
 * 12+13=25
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC5 {

    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
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
    private int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return preorderSumNumbers(root, 0);
    }

    private int preorderSumNumbers(TreeNode node, int sum) {
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
