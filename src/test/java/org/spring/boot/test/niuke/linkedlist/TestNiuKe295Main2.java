package org.spring.boot.test.niuke.linkedlist;

import org.spring.boot.test.niuke.ListNode;
import org.spring.boot.test.niuke.NiuKeUtils;

import java.util.Scanner;
import java.util.Stack;

// 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度
// O(n)，空间复杂度 O(1)。 例如： 给出的链表为
// 1→2→3→4→5→NULL, m=2,n=4,
// 返回
// 1→4→3→2→5→NULL.
// 数据范围： 链表长度 0<size≤1000， 0<m≤n≤size，链表中每个节点的值满足 ∣val∣≤1000
// 要求：时间复杂度 O(n) ，空间复杂度 O(n)
// 进阶：时间复杂度 O(n)，空间复杂度 O(1)
public class TestNiuKe295Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 链表切分子链的元素个数
        int m = in.nextInt();
        int n = in.nextInt();
        ListNode head = NiuKeUtils.createListNode(5);
        System.out.println(head);

        // 反转链表
        ListNode res = reverseBetween(head, m, n);
        NiuKeUtils.printListNode(res);
        System.out.println("finished...");
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @param m    int整型
     * @return ListNode类
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        // 用于指向头结点，在完成反转后返回，防止头节点丢失
        ListNode dummy = new ListNode(-1);
        // next指向头节点
        dummy.next = head;
        ListNode pre = dummy;
        // 将pre移至m节点的前一个节点
        for (int i = 1; i < m; i++) {
            pre = pre.next;
        }
        // 指向m节点
        ListNode cur = pre.next;
        // 进行n-m次反转,反转过程中，cur节点一直不变，因为cur节点最终会位于反转链的末尾，
        // 每一次循环都完成一次将原cur链中cur节点的下一节点放至反转链的头节点位置，并与pre节点拼接的过程
        for (int i = 0; i < (n - m); i++) {
            // 初始化cur节点的下一节点
            ListNode temp = cur.next;
            //cur节点指向它的下下节点,即删除pre链和cur链中的temp节点(cur的下一个节点)
            cur.next = temp.next;
            // 使temp节点指向pre节点的下一节点，即在pre节点的一下节点前拼接temp节点
            temp.next = pre.next;
            // 在pre节点后拼接temp链
            pre.next = temp;
        }
        // 如果从第一个节点开始反转，此时pre = dummy，则pre.next = temp即为dummy.next = temp，首节点会变化
        // 如果不是从第一个节点开始反转，则pre != dummy,则dummy.next =  head，而head在反转过程中未发生变化，首节点不变化
        // 这保证了dummy一直指向头结点，因此不返回head或者pre，而是返回dummy.next
        return dummy.next;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode start = head;
        for (int i = 1; i < m; i++) {
            pre = start;
            start = start.next;
        }
        // reverse
        for (int i = 0; i < n - m; i++) {
            ListNode temp = start.next;
            start.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy.next;
    }
}


