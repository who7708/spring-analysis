package org.spring.boot.test.niuke.linkedlist;

import org.spring.boot.test.niuke.ListNode;
import org.spring.boot.test.niuke.NiuKeUtils;

// 描述
// 输出链表头节点内容
public class TestListNode1 {
    public static void main(String[] args) {
        ListNode head = NiuKeUtils.createListNode(6);
        NiuKeUtils.printListNode(head);
        // 将头节点的后续节点保存
        ListNode next = head.next;
        // 再将头节点的next置空，此时head为仅包含头节点内容
        head.next = null;
        NiuKeUtils.printListNode(head);

        // ListNode pre = null;
        // while (head != null) {:q
        //     ListNode next = head.next;
        //     head.next = pre;
        //     pre = head;
        //     head = next;
        // }
        // NiuKeUtils.printListNode(pre);
        System.out.println("finished...");
    }

}


