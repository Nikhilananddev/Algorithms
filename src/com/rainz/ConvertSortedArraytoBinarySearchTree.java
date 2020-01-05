package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedArraytoBinarySearchTree {
    public static void test(String args[]) {
        int[] input1 = {-10,-3,0,5,9};
        System.out.println(sortedArrayToBST(input1));
    }

    private static TreeNode helper(int[] nums, int start, int end) {
        int len = end - start;
        if (len < 1)
            return null;
        if (len == 1)
            return new TreeNode(nums[start]);
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, start, mid);
        node.right = helper(nums, mid+1, end);
        return node;
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length);
    }

}
