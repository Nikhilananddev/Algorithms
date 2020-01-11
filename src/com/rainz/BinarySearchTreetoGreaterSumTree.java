package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given the root of a binary search tree with distinct values, modify it so that every node has a new value equal to the sum of the values of the original tree that are greater than or equal to node.val.
 */

public class BinarySearchTreetoGreaterSumTree {
    public static void test(String args[]) {
        TreeNode t1 = TreeNode.buildTree("4,1,6,0,2,5,7,null,null,null,3,null,null,null,8");
        System.out.println(bstToGst(t1));
    }

    private static int helper(TreeNode root, int sumSoFar) {
        if (root == null)
            return sumSoFar;
        int sum = helper(root.right, sumSoFar) + root.val;
        root.val = sum;
        return helper(root.left, sum);
    }

    public static TreeNode bstToGst(TreeNode root) {
        // Reverse traversal
        helper(root, 0);
        return root;
    }
}
