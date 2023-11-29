package org.spring.boot.test.niuke.all;

import org.junit.Test;
import org.spring.boot.test.niuke.NiuKeUtils;
import org.spring.boot.test.niuke.TreeNode;

/**
 * NC6 二叉树中的最大路径和
 * <a href="https://www.nowcoder.com/practice/da785ea0f64b442488c125b441a4ba4a?tpId=196&rp=1&ru=%2Fexam%2Foj&qru=%2Fexam%2Foj&sourceUrl=%2Fexam%2Foj&difficulty=&judgeStatus=&tags=&title=&dayCountBigMember=30%E5%A4%A9&gioEnter=menu">...</a>
 * 描述
 * 二叉树里面的路径被定义为:从该树的任意节点出发，经过父=>子或者子=>父的连接，达到任意节点的序列。
 * 注意:
 * 1.同一个节点在一条二叉树路径里中最多出现一次
 * 2.一条路径至少包含一个节点，且不一定经过根节点
 *
 * 给定一个二叉树的根节点root，请你计算它的最大路径和
 *
 * 例如：
 * 给出以下的二叉树，1,2,3
 *
 * 最优路径是:2=>1=>3，或者3=>1=>2，最大路径和=2+1+3=6
 *
 * 数据范围：节点数满足 1≤n≤pow(10,5) ，节点上的值满足 ∣val∣≤1000
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 *
 * {1,2,3}
 * 6
 *
 * 输入：
 * {-20,8,20,#,#,15,6}
 * 返回值：
 * 41
 *
 * 输入：
 * {-2,#,-3}
 * 返回值：
 * -2
 *
 * 题解图片
 * B673751B8180262E6396E8E78FFA1EAD.png
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC6 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        // int[] arr = {-20, 8, 20, 0, 0, 15, 6};
        int[] arr = {-10, 9, 20, 0, 0, 15, 7};
        TreeNode root = NiuKeUtils.createBinaryTree(arr);
        int sum = maxPathSum(root);
        System.out.println(sum);
    }

    int maxSum = Integer.MIN_VALUE;

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param root TreeNode类
     * @return int整型
     */
    public int maxPathSum(TreeNode root) {
        // write code here
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewPath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewPath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }
}
