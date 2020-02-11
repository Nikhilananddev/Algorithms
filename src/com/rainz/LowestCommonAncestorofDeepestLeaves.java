package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.
 * Recall that:
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
 * The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.
 */
public class LowestCommonAncestorofDeepestLeaves {
    public static void test(String args[]) {
        System.out.println(lcaDeepestLeaves(TreeNode.buildTree("1,2,3")));
        System.out.println(lcaDeepestLeaves(TreeNode.buildTree("1,2,3,4")));
        System.out.println(lcaDeepestLeaves(TreeNode.buildTree("1,2,3,4,5")));
    }

    private static int helper(TreeNode root, TreeNode[] res) {
        if (root.left == null && root.right == null) {
            res[0] = root;
            return 1;
        }
        int left = 0, right = 0, ret = 0;
        TreeNode[] lRes = { null };
        TreeNode[] rRes = { null };
        if (root.left != null)
            left = helper(root.left, lRes);
        if (root.right != null)
            right = helper(root.right, rRes);
        if (left == right) {
            ret = left + 1;
            res[0] = root;
        } else if (left > right) {
            ret = left + 1;
            res[0] = lRes[0];
        } else {
            ret = right + 1;
            res[0] = rRes[0];
        }
        return ret;
    }

    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null)
            return null;
        TreeNode[] ans = { null };
        helper(root, ans);
        return ans[0];
    }
}
