package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the sums of the subtrees are maximized.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 */

public class MaximumProductofSplittedBinaryTree {
    public static void test(String args[]) {
        System.out.println(maxProduct(TreeNode.buildTree("1,2,3,4,5,6")));
        System.out.println(maxProduct(TreeNode.buildTree("1,null,2,3,4,null,null,5,6")));
        System.out.println(maxProduct(TreeNode.buildTree("2,3,9,10,7,8,6,5,4,11,1")));
        System.out.println(maxProduct(TreeNode.buildTree("1,1")));
    }

    private static long helper(TreeNode root, List<Long> treeSums) {
        if (root == null)
            return 0;
        long sum = root.val + helper(root.left, treeSums) + helper(root.right, treeSums);
        treeSums.add(sum);
        return sum;
    }

    public static int maxProduct(TreeNode root) {
        final long MODULO = 1000000007;
        List<Long> treeSums = new ArrayList<>();
        long total = helper(root, treeSums);
        long bestSum = 0;
        double minDiff = Double.MAX_VALUE;
        for (long sum: treeSums) {
            double diff = Math.abs(sum-total/2.0);
            if (diff < minDiff) {
                minDiff = diff;
                bestSum = sum;
            }
        }
        return (int)(((total - bestSum) * bestSum) % MODULO);
    }
}
