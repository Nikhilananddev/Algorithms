package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
 * Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 * (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)
 */
public class BinaryTreePruning {
    public static void test(String args[]) {
        System.out.println(pruneTree(TreeNode.buildTree("1,null,0,0,1")));
        System.out.println(pruneTree(TreeNode.buildTree("1,0,1,0,0,0,1")));
        System.out.println(pruneTree(TreeNode.buildTree("1,1,0,1,1,0,1,0")));
    }

    private static boolean helper(TreeNode root) {
        if (root == null)
            return false;
        boolean leftHas1 = helper(root.left);
        boolean rightHas1 = helper(root.right);
        if (!leftHas1)
            root.left = null;
        if (!rightHas1)
            root.right = null;
        return leftHas1 || rightHas1 || root.val == 1;
    }

    public static TreeNode pruneTree(TreeNode root) {
        if (!helper(root))
            return null;
        return root;
    }
}
