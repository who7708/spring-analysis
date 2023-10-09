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
}
