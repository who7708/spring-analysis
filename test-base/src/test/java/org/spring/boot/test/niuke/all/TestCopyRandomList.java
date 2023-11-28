package org.spring.boot.test.niuke.all;

/*
 * To execute Java, please define "static void main" on a class
 *
 * If you define many classes, but you must have a class named TestCopyRandomList and a public property.
 * The TestCopyRandomList class should be the only public class.
 * The TestCopyRandomList class must contain a static method (function) named "main"
 * Do not add any package, like "package main"
 *
 * The TestCase is shown below
 * Input : 1 2
 * Output : 3
 */

class TestCopyRandomList {
    public static void main(String[] args) {
        //
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
        while(root != null) {
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

    static class Node {
        int data;

        Node next;

        Node random;

        public Node(int data) {
            this.data = data;
        }
    }
}