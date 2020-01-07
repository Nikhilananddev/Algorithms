package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
 * The length of path between two nodes is represented by the number of edges between them.
 */

public class LongestUnivaluePath {
    public static void test(String args[]) {
        System.out.println(longestUnivaluePath(TreeNode.buildTree("5,4,5,1,1,null,5")));
        System.out.println(longestUnivaluePath(TreeNode.buildTree("1,4,5,4,4,null,5")));
    }

    /*
     * Recursive, bottom up. Returns {longest leg with same value as node, max length}
     */
    private static int[] helper(TreeNode node, int longest) {
        int uniLen = 0;
        if (node != null) {
            int[] left = helper(node.left, longest);
            int uniLeft = node.left != null && node.left.val == node.val ? left[0] + 1 : 0;
            int[] right = helper(node.right, longest);
            int uniRight = node.right != null && node.right.val == node.val ? right[0] + 1 : 0;
            longest = Math.max(longest, uniLeft + uniRight); // left, node, and right can be a path.
            uniLen = Math.max(uniLeft, uniRight);
            if (left[1] > longest)
                longest = left[1];
            if (right[1] > longest)
                longest = right[1];
        }
        int[] ans = {uniLen, longest};
        return ans;
    }

    public static int longestUnivaluePath(TreeNode root) {
        return helper(root, 0)[1];
    }
}
