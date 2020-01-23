package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 */
public class BinaryTreeLongestConsecutiveSequence {
    public static void test(String args[]) {
        System.out.println(longestConsecutive(TreeNode.buildTree("1,null,3,2,4,null,null,null,5")));
        System.out.println(longestConsecutive(TreeNode.buildTree("2,null,3,2,null,1")));
    }

    private static int helper(TreeNode root, int[] longest) {
        if (root == null)
            return 0;
        int leftLen = helper(root.left, longest);
        int rightLen = helper(root.right, longest);
        int len = 1;
        if (root.left != null && root.left.val == root.val + 1)
            len = leftLen + 1;
        if (root.right != null && root.right.val == root.val + 1 && rightLen + 1 > len)
            len = rightLen + 1;
        if (len > longest[0])
            longest[0] = len;
        return len;
    }


    public static int longestConsecutive(TreeNode root) {
        int[] ans = new int[1];
        helper(root, ans);
        return ans[0];
    }
}
