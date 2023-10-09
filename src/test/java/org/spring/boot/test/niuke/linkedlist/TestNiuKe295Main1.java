package org.spring.boot.test.niuke.linkedlist;

import org.spring.boot.test.niuke.ListNode;
import org.spring.boot.test.niuke.NiuKeUtils;

import java.util.Scanner;
import java.util.Stack;

// 描述
// 给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。
// 数据范围： 0≤n≤1000
// 要求：空间复杂度 O(1) ，时间复杂度 O(n) 。
// 如当输入链表{1,2,3}时，
// 经反转后，原链表变为{3,2,1}，所以对应的输出为{3,2,1}。
public class TestNiuKe295Main1 {
    public static void main(String[] args) {
        ListNode head = NiuKeUtils.createListNode(6);
        // 反转链表
        ListNode res = ReverseList(head);
        NiuKeUtils.printListNode(res);
        System.out.println("finished...");
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public static ListNode ReverseList(ListNode head) {
        // write code here
        if (head == null) {
            return null;
        }

        // 链表反转
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

}


