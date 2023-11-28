package org.spring.boot.test.niuke.all;

import org.spring.boot.test.niuke.TreeNode;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/28
 */
public class TestBinaryTreeHeight {
    public static void main(String[] args) {
        //          1
        //         / \
        //        2   3
        //           /
        //          4
        //           \
        //            5
        // 树高为 4
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node4.right = node5;
        int height = binaryTreeHeight(node1);
        System.out.println(height);
    }

    // 求二叉树树高
    public static int binaryTreeHeight(TreeNode root) {
        if (root == null) {
            // 如果树为空，高度为 0
            return 0;
        }

        return Math.max(binaryTreeHeight(root.right), binaryTreeHeight(root.left)) + 1;
    }
}
