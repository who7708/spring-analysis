package org.spring.boot.test.niuke.linkedlist;

import org.spring.boot.test.niuke.ListNode;
import org.spring.boot.test.niuke.NiuKeUtils;

// 删除链表的倒数第k个节点
public class TestListNode2 {
    public static void main(String[] args) {
        ListNode head = NiuKeUtils.createListNode(6);
        NiuKeUtils.printListNode(head);
        ListNode res = deleteLastNthNode(head, 1);
        NiuKeUtils.printListNode(res);
        // ListNode res = deleteLastNthNode(head, 2);
        // NiuKeUtils.printListNode(res);
        // ListNode res = deleteLastNthNode(head, 3);
        // NiuKeUtils.printListNode(res);
        // ListNode res = deleteLastNthNode(head, 4);
        // NiuKeUtils.printListNode(res);
        // ListNode res = deleteLastNthNode(head, 5);
        // NiuKeUtils.printListNode(res);
        // ListNode res = deleteLastNthNode(head, 6);
        // NiuKeUtils.printListNode(res);
        System.out.println("finished...");
    }

    // 1,2,3,4,5,6 删除倒数第2个，也就是正数第5个
    // 删除链表的倒数第k个节点
    // 倒数第k个，也就是正数的 n - (k - 1)
    public static ListNode deleteLastNthNode(ListNode head, int k) {
        // 先计算总数量
        int count = 0;
        ListNode next = head;
        while (next != null) {
            next = next.next;
            count++;
        }

        next = head;
        // 正数需要删除的下标
        int idx = count - k;
        int counter = 1;
        while (next != null) {
            // 如果是头元素，则直接返回
            if (idx == 0) {
                return next.next;
            }
            if (counter == idx) {
                // NiuKeUtils.printListNode(next);
                // 将删除的元素进行断链即可
                ListNode del = next.next;
                next.next = del.next;
                break;
            }
            counter++;
            next = next.next;
        }
        return head;

    }

}


