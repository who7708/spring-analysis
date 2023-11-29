package org.spring.boot.test.niuke.all;

import org.junit.Test;
import org.spring.boot.test.niuke.TreeNode;

/**
 * NC13 二叉树的最大深度
 * <a
 * href="https://www.nowcoder.com/practice/8a2b2bf6c19b4f23a9bdb9b233eefa73?tpId=196&rp=1&ru=%2Fexam%2Foj&qru=%2Fexam%2Foj&sourceUrl=%2Fexam%2Foj&difficulty=&judgeStatus=&tags=&title=&dayCountBigMember=30%E5%A4%A9&gioEnter=menu">...</a>
 * 描述
 * 求给定二叉树的最大深度，
 * 深度是指树的根节点到任一叶子节点路径上节点的数量。
 * 最大深度是所有叶子节点的深度的最大值。
 * （注：叶子节点是指没有子节点的节点。）
 *
 *
 * 数据范围： 0≤n≤100000，树上每个节点的val满足 ∣val∣≤100
 * 要求： 空间复杂度 O(1),时间复杂度 O(n)
 * {@link TestBinaryTreeHeight}
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC13 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        //          1
        //         / \
        //        2   3
        //           /
        //          4
        //           \
        //            5
        // 树高为 4
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node4.right = node5;

        long begin = System.nanoTime();
        int maxDepth = maxDepth(node1);
        System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
        System.out.println("maxDepth = " + maxDepth);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param root TreeNode类
     * @return int整型
     */
    public int maxDepth(TreeNode root) {
        // write code here
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
