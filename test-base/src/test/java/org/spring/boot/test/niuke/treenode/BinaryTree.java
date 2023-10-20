package org.spring.boot.test.niuke.treenode;

import java.util.Stack;

import org.spring.boot.test.niuke.NiuKeUtils;
import org.spring.boot.test.niuke.TreeNode;

public class BinaryTree {

    public TreeNode root = null;

    public BinaryTree(int[] array) {
        root = NiuKeUtils.createBinaryTree(array, 0);
        // NiuKeUtils.printTreeNode(root, "root");
    }

    private void showData(TreeNode node) {
        System.out.print(node);
    }

    // 递归遍历二叉树

    // 先序遍历（前序遍历）
    public void preOrder(TreeNode node) {
        if (node != null) {
            showData(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 中序遍历
    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            showData(node);
            inOrder(node.right);
        }
    }
    // 后序遍历

    public void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            showData(node);
        }
    }

    // 非递归遍历二叉树

    // 前序遍历

    public void noRecursionPreOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
            while (!stack.empty()) {
                node = stack.pop();
                showData(node);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }

            }
        }
    }

    // 中序遍历
    public void noRecursionInOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = node;
        while (p != null || stack.size() > 0) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (stack.size() > 0) {
                p = stack.pop();
                showData(p);
                p = p.right;
            }
        }
    }

    //后序遍历 ,需要记录遍历过的节点
    public void noRecursionPostOrder(TreeNode node) {
        TreeNode pre = node;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null) {
            for (; node.left != null; node = node.left) {
                stack.push(node);
            }
            while (node != null && (node.right == null || node.right == pre)) {
                showData(node);
                pre = node;
                if (stack.empty()) {
                    return;
                }
                node = stack.pop();
            }
            stack.push(node);
            node = node.right;
        }
    }

    public static void main(String[] args) {
        //顺序存储的满二叉树或者完全二叉树
        // int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // int[] arr = {1, 2, 0, 3, 4, 8, 7, 9, 5};
        int[] arr = {1, 2, 0, 3, 4, 8, 7, 9};
        BinaryTree bt = new BinaryTree(arr);
        System.out.println("递归前序遍历：");
        bt.preOrder(bt.root);
        System.out.println();
        System.out.println("递归中序遍历：");
        bt.inOrder(bt.root);
        System.out.println();
        System.out.println("递归后序遍历：");
        bt.postOrder(bt.root);
        System.out.println();
        System.out.println("非递归前序遍历：");
        bt.noRecursionPreOrder(bt.root);
        System.out.println();
        System.out.println("非递归中序遍历：");
        bt.noRecursionInOrder(bt.root);
        System.out.println();
        System.out.println("非递归后序遍历：");
        bt.noRecursionPostOrder(bt.root);
        System.out.println();
    }

}
