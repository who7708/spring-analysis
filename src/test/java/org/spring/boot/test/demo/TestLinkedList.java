package org.spring.boot.test.demo;

import lombok.Data;
import org.junit.Test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023.09.18 018
 */
public class TestLinkedList {
    // 如何判断链表是否有环？

    /**
     * <pre>
     * 3---2---0---1
     *     |_______|
     * </pre>
     */
    @Test
    public void testHasCycle() throws Exception {
        System.out.println("===== testHasCycle =====");
        // 定义一个有环链表
        ListNode head = new ListNode();
        head.value = 3;

        ListNode node2 = new ListNode();
        node2.value = 2;

        ListNode node0 = new ListNode();
        node0.value = 0;

        ListNode node1 = new ListNode();
        node1.value = 1;

        head.next = node2;
        node2.next = node0;
        node0.next = node1;
        node1.next = node2;

        // boolean hasCycle = hasCycle(head);
        // System.out.println(hasCycle(head));
        ListNode cycleNodeEntry = detectNodeEntry(head);
        System.out.println(cycleNodeEntry);

    }

    /**
     * 判断链表是否有环
     */
    private boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 找到链表的入环节点
     */
    private ListNode detectNodeEntry(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode p = head;
                while (p != slow) {
                    p = p.next;
                    slow = slow.next;
                }
                return p;
            }
        }
        return null;
    }

    @Data
    static class ListNode {
        private ListNode next;

        private int value;

        @Override
        public String toString() {
            return "ListNode{" +
                    "value=" + value +
                    '}';
        }
    }
}
