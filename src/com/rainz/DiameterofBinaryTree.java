package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 */
public class DiameterofBinaryTree {
    public static void test(String args[]) {
        System.out.println(diameterOfBinaryTree(Main.TreeNode.buildTree("1,2,3,4,5")));
    }

    private static int helper(TreeNode root, int[] ans) {
        if (root == null)
            return 0;
        int leftLen = helper(root.left, ans);
        int rightLen = helper(root.right, ans);
        int totalLen = leftLen + rightLen + 1;
        if (totalLen > ans[0])
            ans[0] = totalLen;
        return 1 + Math.max(leftLen, rightLen);
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int[] ans = {0};
        helper(root, ans);
        return ans[0]-1; // length is # nodes - 1
    }
}
