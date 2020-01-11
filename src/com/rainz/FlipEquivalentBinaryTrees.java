package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 * Write a function that determines whether two binary trees are flip equivalent.  The trees are given by root nodes root1 and root2.
 */

public class FlipEquivalentBinaryTrees {
    public static void test(String args[]) {
        TreeNode root11 = TreeNode.buildTree("1,2,3,4,5,6,null,null,null,7,8");
        TreeNode root12 = TreeNode.buildTree("1,3,2,null,6,4,5,null,null,null,null,8,7");
        System.out.println(flipEquiv(root11, root12));
    }

    private static boolean cmpTreeNode(TreeNode root1, TreeNode root2)
    {
        if (root1 == null)
            return root2 == null;
        if (root2 == null)
            return false;
        return root1.val == root2.val;
    }

    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (!cmpTreeNode(root1, root2))
            return false;
        if (root1 == null && root2 == null)
            return true;
        if (cmpTreeNode(root1.left, root2.left) && cmpTreeNode(root1.right, root2.right))
            return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        if (cmpTreeNode(root1.left, root2.right) && cmpTreeNode(root1.right, root2.left))
            return flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
        return false;
    }

}
