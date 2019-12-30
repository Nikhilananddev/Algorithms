package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a non-empty binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 */

public class BinaryTreeMaximumPathSum {
    public static void test(String args[]) {
        System.out.println(maxPathSum(TreeNode.buildTree("1,2,3")));
        System.out.println(maxPathSum(TreeNode.buildTree("-10,9,20,null,null,15,7")));
    }

    public static int[] helper(TreeNode root) {
        // Note the default value should be MIN_VALUE, not 0.
        // Otherwise trees with negative values will return 0.
        int[] result = {Integer.MIN_VALUE, Integer.MIN_VALUE}; // maxSum, max leg sum
        if (root == null)
            return result;

        int[] lResult = helper(root.left);
        int[] rResult = helper(root.right);

        // Compute max leg sum.
        result[1] = Math.max(lResult[1], rResult[1]);
        if (result[1] <= 0)
            result[1] = 0;
        result[1] += root.val;

        // Compute max path sum.
        result[0] = root.val;
        if (lResult[1] > 0)
            result[0] += lResult[1];
        if (rResult[1] > 0)
            result[0] += rResult[1];
        // So far the max path sum passing through root has been computed.
        // Now compare it with left max and right max.
        int tmpMax = Math.max(lResult[0], rResult[0]);
        if (tmpMax > result[0])
            result[0] = tmpMax;

        return result;
    }

    public static int maxPathSum(TreeNode root) {
        int[] result = helper(root);
        return result[0];
    }
}
