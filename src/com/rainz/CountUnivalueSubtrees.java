package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value.
 */

public class CountUnivalueSubtrees {
    public static void test(String args[]) {
        System.out.println(countUnivalSubtrees(TreeNode.buildTree("5,1,5,5,5,null,5")));
    }

    /*
     * Returns {unival or null, unival subtree count}
     * Root cannot be null
     */
    private static Integer[] helper(TreeNode root) {
        Integer[] ans = {root.val, 0};
        if (root.left != null) {
            Integer[] ret = helper(root.left);
            if (ret[0] == null || ret[0].intValue() != root.val)
                ans[0] = null;
            ans[1] += ret[1]; // unival subtree count from left
        }
        if (root.right != null) {
            Integer[] ret = helper(root.right);
            if (ret[0] == null || ret[0].intValue() != root.val)
                ans[0] = null;
            ans[1] += ret[1]; // unival subtree count from right
        }
        if (ans[0] != null)
            ++ans[1]; // left=right=root, count root as a unival subtree
        return ans;
    }

    public static int countUnivalSubtrees(TreeNode root) {
        if (root == null)
            return 0;
        return helper(root)[1];
    }
}
