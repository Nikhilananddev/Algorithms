package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 */
public class MinimumAbsoluteDifferenceinBST {
    public static void test(String args[]) {
        System.out.println(getMinimumDifference(TreeNode.buildTree("1,null,3,2")));
    }

    // Return {min, max, minDiff}
    private static int[] helper(TreeNode root) {
        int min = root.val, max = root.val, minDiff = Integer.MAX_VALUE;
        if (root.left != null) {
            int[] left = helper(root.left);
            min = left[0];
            int diff = root.val - left[1];
            minDiff = Math.min(left[2], diff);
        }
        if (root.right != null) {
            int[] right = helper(root.right);
            max = right[1];
            minDiff = Math.min(right[2], minDiff);
            int diff = right[0] - root.val;
            minDiff = Math.min(minDiff, diff);
        }
        int[] result = {min, max, minDiff};
        return result;
    }

    public static int getMinimumDifference(TreeNode root) {
        if (root == null)
            return 0;
        return helper(root)[2];
    }
}
