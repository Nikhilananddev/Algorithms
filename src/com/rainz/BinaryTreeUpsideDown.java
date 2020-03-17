package com.rainz;

import com.rainz.Main.TreeNode;
/*
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 */

public class BinaryTreeUpsideDown {
    public static void test(String args[]) {
        System.out.println(upsideDownBinaryTree((TreeNode.buildTree("1,2,3,4,5"))));
        System.out.println(upsideDownBinaryTree((TreeNode.buildTree("1,2"))));
    }

    /* No need to return two tree nodes. The bottom right node is the original left node */
    public static TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null)
            return root;
        TreeNode l = root.left, r = root.right;
        TreeNode ans = upsideDownBinaryTree(l);
        // Now attach right and root
        l.left = r;
        l.right = root;
        root.left = null;
        root.right = null;
        // No need to set left & right of "r" to null because the problem states all right children are leaves or null.
        return ans;
    }

    private static TreeNode[] helper(TreeNode root) {
        if (root == null) {
            TreeNode[] res = {null, null};
            return res;
        }
        if (root.left == null) {
            TreeNode[] res = {root, root};
            return res;
        }
        TreeNode[] lRes = helper(root.left);
        lRes[1].left = root.right;
        lRes[1].right = root;
        if (root.right != null) {
            root.right.left = null;
            root.right.right = null;
        }
        root.left = null;
        root.right = null;
        TreeNode[] res = {lRes[0], root};
        return res;
    }

    public static TreeNode upsideDownBinaryTreePassed(TreeNode root) {
        TreeNode[] ans = helper(root);
        return ans[0];
    }

}
