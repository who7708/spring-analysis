package org.spring.boot.test.niuke.linkedlist;

import org.spring.boot.test.niuke.ListNode;
import org.spring.boot.test.niuke.NiuKeUtils;

import java.util.Scanner;
import java.util.Stack;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 1.
// k链表翻转。给出一个链表和一个数k，比如链表1→2→3→4→5→6，k=2，则翻转后2→1→4→3→6→5，若k=3,翻转后3→2→1→6→5→4，若k=4，翻转后4→3→2→1→5→6，用程序实现。
public class TestNiuKe295Main3_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 链表切分子链的元素个数
        int count = in.nextInt();
        ListNode head = NiuKeUtils.createListNode(6);
        System.out.println(head);

        // 反转链表
        ListNode res = reverseKGroup(head, count);
        NiuKeUtils.printListNode(res);
        System.out.println("finished...");
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @param k    int整型
     * @return ListNode类
     */
    private static ListNode reverseKGroup(ListNode head, int k) {
        // write code here
        if (head == null || head.next == null || k < 2) {
            return head;
        }

        // 根据k个元素进行分组
        // 保存链表长度
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            len++;
        }

        ListNode res = new ListNode(-1);
        ListNode tail = res;
        ListNode p = head;
        Stack<ListNode> stack = new Stack<>();
        while (len >= k) {
            len -= k;
            // 分组的节点压入栈中
            for (int i = 0; i < k; i++) {
                stack.push(p);
                p = p.next;
            }

            // 节点出栈反转
            for (int i = 0; i < k; i++) {
                ListNode node = stack.pop();
                tail.next = node;
                node.next = null;
                tail = node;
            }
        }

        tail.next = p;
        return res.next;
    }
}


