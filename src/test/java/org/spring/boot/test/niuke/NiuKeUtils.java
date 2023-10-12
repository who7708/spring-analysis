package org.spring.boot.test.niuke;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/09 009
 */
public class NiuKeUtils {

    // 创建一个链表
    public static ListNode createListNode(int len) {
        ListNode head = new ListNode(1);
        ListNode next = head;
        for (int i = 2; i <= len; i++) {
            ListNode newNode = new ListNode(i);
            next.next = newNode;
            next = newNode;
        }
        return head;
    }

    public void addNode(ListNode head, ListNode node) {
        while (head.next != null) {
            head = head.next;
        }
        head.next = node;
    }

    public static void printListNode(ListNode head) {
        // 打印链表
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // {1,2,0,3,4}
    // 创建完全二叉树
    public static TreeNode createBinaryTree(int[] arr) {
        return createBinaryTree(arr, 0);
    }

    public static TreeNode createBinaryTree(int[] arr, int index) {
        TreeNode treeNode = null;
        if (index < arr.length) {
            treeNode = new TreeNode(arr[index]);
            // 对于顺序存储的完全二叉树，如果某个节点的索引为index，其对应的左子树的索引为2*index+1，右子树为2*index+1
            treeNode.left = createBinaryTree(arr, 2 * index + 1);
            treeNode.right = createBinaryTree(arr, 2 * index + 2);
        }
        return treeNode;
    }

    public static void printTreeNode(TreeNode root, String pos) {
        // if (root == null) {
        //     System.out.println("root is null");
        //     return;
        // }
        // if ("root".equals(pos)) {
        //     System.out.println(pos + ".val = " + (root != null ? root.val : null));
        // }
        // if ("left".equals(pos)) {
        //     System.out.println(pos + ".val = " + (root != null ? root.val : null));
        // }
        // if ("right".equals(pos)) {
        //     System.out.println(pos + ".val = " + (root != null ? root.val : null));
        // }
        System.out.println(pos + ".val = " + (root != null ? root.val : null));
        if (root != null) {
            printTreeNode(root.left, "left");
            printTreeNode(root.right, "right");
        }
        // if (root != null && root.left != null) {
        //     printTreeNode(root.left, "left");
        // }
        // if (root != null && root.right != null) {
        //     printTreeNode(root.right, "right");
        // }
    }
}
