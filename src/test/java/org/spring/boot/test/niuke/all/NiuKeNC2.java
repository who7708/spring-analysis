package org.spring.boot.test.niuke.all;

import org.junit.Test;
import org.spring.boot.test.niuke.ListNode;
import org.spring.boot.test.niuke.NiuKeUtils;

/**
 * NC2 重排链表
 * 描述
 * 将给定的单链表L: L0->L1->...->Ln-1->Ln
 * 重新排序为：L0->Ln-1->L1->Ln-2->...
 * 要求使用原地算法，不能只改变节点内部的值，需要对实际的节点进行交换。
 *
 * 数据范围：链表长度 0≤n≤20000 ，链表中每个节点的值满足 0≤val≤1000
 *
 * 要求：空间复杂度 O(n) 并在链表上进行操作而不新建链表，时间复杂度 O(n)
 * 进阶：空间复杂度 O(1) ， 时间复杂度 O(n)
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC2 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        ListNode head = NiuKeUtils.createListNode(5);
        // ListNode head = NiuKeUtils.createListNode(6);
        NiuKeUtils.printListNode(head);
        reorderList(head);
        NiuKeUtils.printListNode(head);
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        // 找中点，链表分成两个
        // slow 步长为1, fast步长为2, fast走完,则slow正好走一半,再将slow的后续节点置空,则原始链表就是前半链表了
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 找到中间节点后，将后继节点断链，此时原始head就是前半链表
        // 中间节点.next即为后半链表,然后进行反转
        ListNode newHead = slow.next;
        slow.next = null;

        // 第二个链表倒置，反转后半部分链表
        newHead = NiuKeUtils.reverseListNode(newHead);

        // 链表节点依次连接,如:
        // 前半: 1->2->3, 后半反转: 5->4
        // 拼装后 1-5-2-4-3
        // 在while里操作 head.next 等操作,并不会影响方法外原始head的地址指向,仅会操作对象内部的内容变更
        // 好理解点,可以再增加个变量
        // ListNode node = head;
        // head 就是原始链表最终操作结果,node就是临时链表操作结果. 即如下内容:
        // ListNode node = head;
        // while (newHead != null) {
        //     ListNode temp = newHead.next;
        //     newHead.next = node.next;
        //     node.next = newHead;
        //     node = newHead.next;
        //     newHead = temp;
        // }
        // 满足空间复杂度 O(1)
        while (newHead != null) {
            ListNode temp = newHead.next;
            newHead.next = head.next;
            head.next = newHead;
            head = newHead.next;
            newHead = temp;
        }
    }

}
