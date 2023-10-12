package org.spring.boot.test.niuke;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class TreeNode {
    public int val;

    public TreeNode left = null;

    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val + " ";
    }
}
