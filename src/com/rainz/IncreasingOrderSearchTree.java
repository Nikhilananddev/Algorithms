package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.
 */

public class IncreasingOrderSearchTree {
    public static void test(String args[]) {
        System.out.println(increasingBST(TreeNode.buildTree("5,3,6,2,4,null,8,1,null,null,null,7,9")));
    }
    // Returns first and last tree nodes
    private static TreeNode[] helper(TreeNode root) {
        TreeNode leftTree = root.left;
        TreeNode rightTree = root.right;
        root.left = null;
        TreeNode head = root, tail = root;
        if (leftTree != null) {
            TreeNode[] res = helper(leftTree);
            head = res[0];
            res[1].right = root;
        }
        if (rightTree != null) {
            TreeNode[] res = helper(rightTree);
            root.right = res[0];
            tail = res[1];
        }
        TreeNode[] res = {head, tail};
        return res;
    }
    public static TreeNode increasingBST(TreeNode root) {
        if (root == null)
            return null;
        return helper(root)[0];
    }
}
