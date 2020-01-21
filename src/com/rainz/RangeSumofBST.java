package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
 * The binary search tree is guaranteed to have unique values.
 */
public class RangeSumofBST {
    public static void test(String args[]) {
        System.out.println(rangeSumBST(TreeNode.buildTree("10,5,15,3,7,null,18"), 7, 15));
        System.out.println(rangeSumBST(TreeNode.buildTree("10,5,15,3,7,13,18,1,null,6"), 6, 10));
    }
    public static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null)
            return 0;
        if (root.val < L)
            return rangeSumBST(root.right, L, R);
        if (root.val > R)
            return rangeSumBST(root.left, L, R);
        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }
}
