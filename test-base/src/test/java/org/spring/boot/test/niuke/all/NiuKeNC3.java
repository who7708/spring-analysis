package org.spring.boot.test.niuke.all;

import org.junit.Test;
import org.spring.boot.test.niuke.ListNode;
import org.spring.boot.test.niuke.NiuKeUtils;

/**
 * NC3 链表中环的入口结点
 * <a href="https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=196&rp=1&ru=%2Fexam%2Foj&qru=%2Fexam%2Foj&sourceUrl=%2Fexam%2Foj&difficulty=&judgeStatus=&tags=&title=&dayCountBigMember=30%E5%A4%A9&gioEnter=menu">...</a>
 * 具体做法：
 *
 * step 1：使用BM6.判断链表中是否有环中的方法判断链表是否有环，并找到相遇的节点。
 * step 2：慢指针继续在相遇节点，快指针回到链表头，两个指针同步逐个元素逐个元素开始遍历链表。
 * step 3：再次相遇的地方就是环的入口。
 *
 * 动态演示如图
 * docs/8B355F0FCD615249270C1B1DEBC84C52.gif
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/13
 */
public class NiuKeNC3 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        // 线性链表
        // ListNode head = NiuKeUtils.createListNode(5);

        // 环形链表
        // 1-2-3-4-5-3...
        // ListNode head = NiuKeUtils.createCycleListNode1();
        ListNode head = NiuKeUtils.createCycleListNode2();

        ListNode hasCycle = hasCycle(head);
        System.out.println(hasCycle);

        ListNode entryNode = EntryNodeOfLoop(head);
        System.out.println(entryNode);
    }

    // 链表中环的入口节点
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode slow = hasCycle(pHead);
        // 没有环
        if (slow == null) {
            return null;
        }
        // 快指针回到表头
        ListNode fast = pHead;
        // 再次相遇即是环入口
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    // 判断有没有环，返回相遇的地方
    public ListNode hasCycle(ListNode head) {
        // 先判断链表为空的情况
        if (head == null) {
            return null;
        }
        // 快慢双指针
        ListNode slow = head;
        ListNode fast = head;
        // 如果没环快指针会先到链表尾
        while (fast != null && fast.next != null) {
            // 慢指针移动一步
            slow = slow.next;
            // 快指针移动两步
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        //到末尾说明没有环，返回null
        return null;
    }
}
