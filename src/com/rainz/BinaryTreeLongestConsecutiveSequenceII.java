package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 * Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 */

public class BinaryTreeLongestConsecutiveSequenceII {
    public static void test(String args[]) {
        System.out.println(longestConsecutive(TreeNode.buildTree("1,2,3")));
        System.out.println(longestConsecutive(TreeNode.buildTree("2,1,3")));
        System.out.println(longestConsecutive(TreeNode.buildTree("1")));
    }

    /*
     * Similar to BinaryTreeLongestConsecutiveSequence, but we keep track of lengths both increasing & decreasing sequences.
     */
    private static int[] helper(TreeNode root, int[] longest) {
        if (root == null)
            return new int[]{0,0};
        int[] result = {1, 1}; // inc length, dec length
        int[] left = helper(root.left, longest);
        int[] right = helper(root.right, longest);
        if (root.left == null || root.val == root.left.val + 1)
            result[0] = Math.max(result[0], left[0] + 1);
        if (root.right == null || root.val == root.right.val + 1)
            result[0] = Math.max(result[0], right[0] + 1);
        if (root.left == null || root.val == root.left.val - 1)
            result[1] = Math.max(result[1], left[1] + 1);
        if (root.right == null || root.val == root.right.val - 1)
            result[1] = Math.max(result[1], right[1] + 1);

        int totalLen = result[0] + result[1] - 1;
        if (totalLen > longest[0])
            longest[0] = totalLen;
        return result;
    }

    public static int longestConsecutive(TreeNode root) {
        int[] ans = new int[1];
        helper(root, ans);
        return ans[0];
    }
}
