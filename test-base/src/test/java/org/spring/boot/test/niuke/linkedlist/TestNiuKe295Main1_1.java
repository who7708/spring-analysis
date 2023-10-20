package org.spring.boot.test.niuke.linkedlist;

import org.spring.boot.test.niuke.ListNode;
import org.spring.boot.test.niuke.NiuKeUtils;

import java.util.Scanner;

// 反转前n个节点，需要保存第n+1个节点，与反转后的前N个节点链表进行拼接。
public class TestNiuKe295Main1_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ListNode head = NiuKeUtils.createListNode(6);
        // 反转链表
        // ListNode res = reverseFirstN(head, n);
        ListNode res = reverseLastN(head, n);
        NiuKeUtils.printListNode(res);
        System.out.println("finished...");
    }

    /**
     * 2.反转链表前n个节点 非递归
     */
    public static ListNode reverseFirstN(ListNode head, int n) {
        ListNode nNext = head;
        for (int i = 0; i < n; i++) {
            nNext = nNext.next;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != nNext) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        head.next = nNext;
        return pre;
    }

    // 反转链表后n个节点
    public static ListNode reverseLastN(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode cur = slow.next;
        ListNode pre = null;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        slow.next = pre;
        return head;
    }

}


