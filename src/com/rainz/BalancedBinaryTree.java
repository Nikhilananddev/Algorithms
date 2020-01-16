package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 */

public class BalancedBinaryTree {
    public static void test(String args[]) {
        System.out.println(isBalanced(TreeNode.buildTree("3,9,20,null,null,15,7")));
        System.out.println(isBalanced(TreeNode.buildTree("1,2,2,3,3,null,null,4,4")));
    }
    /* Return height of deepest subtree, or -1 if one of its subtrees is not balanced */
    private static int treeHeight(TreeNode root) {
        if (root == null)
            return 0;
        int hLeft = treeHeight(root.left);
        int hRight = treeHeight(root.right);
        if (hLeft < 0 || hRight < 0)
            return -1;
        int hDiff = hLeft - hRight;
        if (Math.abs(hDiff) > 1)
            return -1;
        return (hDiff >= 0 ? hLeft : hRight) + 1;
    }

    public static boolean isBalanced(TreeNode root) {
        return treeHeight(root) >= 0;
    }
}
