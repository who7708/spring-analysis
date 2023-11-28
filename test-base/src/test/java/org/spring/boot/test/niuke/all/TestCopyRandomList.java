package org.spring.boot.test.niuke.all;

/**
 * <a href="https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/">...</a>
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 *
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 */
class TestCopyRandomList {
    public static void main(String[] args) {
        // 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
        // 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
        {
            Node node7 = new Node(7);
            Node node13 = new Node(13);
            Node node11 = new Node(11);
            Node node10 = new Node(10);
            Node node1 = new Node(1);
            node7.next = node13;
            node7.random = null;

            node13.next = node11;
            node13.random = node7;

            node11.next = node10;
            node11.random = node1;

            node10.next = node1;
            node10.random = node11;

            node1.random = node7;

            Node node = copyRandomList(node7);
            System.out.println(node);
        }

        // 输入：head = [[1,1],[2,1]]
        // 输出：[[1,1],[2,1]]
        {
            Node node1 = new Node(1);
            Node node2 = new Node(2);

            node1.next = node2;
            node1.random = node2;

            node2.random = node2;

            Node node = copyRandomList(node1);
            System.out.println(node);
        }
        // 输入：head = [[3,null],[3,0],[3,null]]
        // 输出：[[3,null],[3,0],[3,null]]
        {
            Node node30 = new Node(3);
            Node node31 = new Node(3);
            Node node32 = new Node(3);

            node30.next = node31;
            node31.next = node32;

            node31.random = node30;

            Node node = copyRandomList(node30);
            System.out.println(node);
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        Node root = head;
        while (root != null) {
            Node temp = new Node(root.data);
            temp.next = root.next;
            root.next = temp;
            root = root.next.next;
        }

        root = head;
        while (root != null) {
            if (root.random != null) {
                root.next.random = root.random.next;
            }
            root = root.next.next;
        }

        root = head;
        Node copy = head.next;
        Node copyHead = head.next;
        while (root != null) {
            root.next = root.next.next;
            root = root.next;
            if (copy.next != null) {
                copy.next = copy.next.next;
                copy = copy.next;
            }
        }
        return copyHead;
    }

    // Definition for a Node.
    static class Node {
        int data;

        Node next;

        Node random;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.random = null;
        }
    }
}