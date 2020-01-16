package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 */
public class MaximumAverageSubtree {
    public static void test(String args[]) {
        System.out.println(maximumAverageSubtree(TreeNode.buildTree("5,6,1")));
    }

    private static double[] helper(TreeNode root, double maxSoFar) {
        if (root == null) {
            double[] result = {0, 0, maxSoFar}; // sum, count, max
            return result;
        }
        double[] rLeft = helper(root.left, maxSoFar);
        maxSoFar = rLeft[2];
        double[] rRight = helper(root.right, maxSoFar);
        maxSoFar = rRight[2];
        double sum = rLeft[0] + rRight[0] + root.val;
        double count = rLeft[1] + rRight[1] + 1;
        double avg = sum / count;
        if (avg > maxSoFar)
            maxSoFar = avg;
        double[] result = {sum, count, maxSoFar};
        return result;
    }

    public static double maximumAverageSubtree(TreeNode root) {
        double[] result = helper(root, 0);
        return result[2];
    }
}
