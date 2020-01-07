package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 */
public class MaximumBinaryTree {
    public static void test(String args[]) {
        int[] input1 = {3,2,1,6,0,5};
        System.out.println(constructMaximumBinaryTree(input1));
    }

    private static TreeNode helper(int[] nums, int start, int end) {
        if (start >= end)
            return null;
        int maxIdx = start;
        for (int i = start + 1; i < end; ++i) {
            if (nums[maxIdx] < nums[i])
                maxIdx = i;
        }
        TreeNode node = new TreeNode(nums[maxIdx]);
        node.left = helper(nums, start, maxIdx);
        node.right = helper(nums, maxIdx+1, end);
        return node;
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return helper(nums, 0, nums.length);
    }
}
