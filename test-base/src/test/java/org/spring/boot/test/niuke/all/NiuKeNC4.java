package org.spring.boot.test.niuke.all;

import org.junit.Test;
import org.spring.boot.test.niuke.ListNode;
import org.spring.boot.test.niuke.NiuKeUtils;

/**
 * NC4 判断链表中是否有环
 * <a href="https://www.nowcoder.com/practice/650474f313294468a4ded3ce0f7898b9?tpId=196&rp=1&ru=%2Fexam%2Foj&qru=%2Fexam%2Foj&sourceUrl=%2Fexam%2Foj&difficulty=&judgeStatus=&tags=&title=&dayCountBigMember=30%E5%A4%A9&gioEnter=menu">...</a>
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC4 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        ListNode head = NiuKeUtils.createListNode(5);
        // ListNode head = NiuKeUtils.createCycleListNode1();
        // ListNode head = NiuKeUtils.createCycleListNode2();
        System.out.println("hasCycle(head) = " + hasCycle(head));
    }

    // 判断是否有环
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
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
}
