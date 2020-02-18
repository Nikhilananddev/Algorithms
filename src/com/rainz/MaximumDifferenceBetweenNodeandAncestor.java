package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 */

public class MaximumDifferenceBetweenNodeandAncestor {
    public static void test(String args[]) {
        System.out.println(maxAncestorDiff(TreeNode.buildTree("8,3,10,1,6,null,14,null,null,4,7,13")));
    }

    private static void helper(TreeNode root, int max, int min, int[] ans) {
        if (root == null)
            return;
        int diff = Math.abs(max - root.val);
        diff = Math.max(diff, Math.abs(min - root.val));
        if (diff > ans[0])
            ans[0] = diff;
        if (root.val > max)
            max = root.val;
        if (root.val < min)
            min = root.val;
        helper(root.left, max, min, ans);
        helper(root.right, max, min, ans);
    }

    public static int maxAncestorDiff(TreeNode root) {
        int[] ans = { 0 };
        helper(root, root.val, root.val, ans);
        return ans[0];
    }
}
