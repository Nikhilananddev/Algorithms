package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class MaximumDepthofBinaryTree {
    public static void test(String args[]) {
        System.out.println(maxDepth(TreeNode.buildTree("3,9,20,null,null,15,7")));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
