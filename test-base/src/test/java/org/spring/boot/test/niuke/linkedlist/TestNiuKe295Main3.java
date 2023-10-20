package org.spring.boot.test.niuke.linkedlist;

import org.spring.boot.test.niuke.ListNode;
import org.spring.boot.test.niuke.NiuKeUtils;

import java.util.Scanner;
import java.util.Stack;

// 描述
// 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
// 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
// 你不能更改节点中的值，只能更改节点本身。
//
// 数据范围： 0≤n≤2000 ， 1≤k≤2000 ，链表中每个元素都满足 0≤val≤1000
// 要求空间复杂度 O(1)，时间复杂度 O(n)
// 例如：
// 给定的链表是 1→2→3→4→5
// 对于 k=2 , 你应该返回 2→1→4→3→5
// 对于 k=3 , 你应该返回 3→2→1→4→5
public class TestNiuKe295Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 链表切分子链的元素个数
        int count = in.nextInt();
        ListNode head = NiuKeUtils.createListNode(6);
        System.out.println(head);
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
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        while (len >= k) {
            len -= k;
            // 分组的节点压入栈中
            for (int i = 0; i < k; i++) {
                ListNode currNode = new ListNode(p.val);
                stack.push(currNode);
                p = p.next;
            }

            // 节点出栈反转
            for (int i = 0; i < k; i++) {
                ListNode node = stack.pop();
                tail.next = node;
                tail = node;
            }
        }

        tail.next = p;
        System.out.println(len);
        return res.next;
    }
}
