package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 */

public class KthSmallestElementinaBST {
    public static void test(String args[]) {
        System.out.println(kthSmallest(TreeNode.buildTree("3,1,4,null,2"), 1));
        System.out.println(kthSmallest(TreeNode.buildTree("5,3,6,2,4,null,null,1"), 3));
    }

    private static int[] helper(TreeNode root, int k) {
        int[] result = {0, -1};
        if (root == null)
            return result;
        int[] left = helper(root.left, k);
        if (left[1] >= 0) {
            result[1] = left[1];
            return result;
        }
        k -= left[0] + 1;
        if (k == 0) {
            result[1] = root.val;
            return result;
        }
        int[] right = helper(root.right, k);
        if (right[1] >= 0) {
            result[1] = right[1];
            return result;
        }
        result[0] = left[0] + 1 + right[0];
        return result;
    }

    public static int kthSmallest(TreeNode root, int k) {
        return helper(root, k)[1];
    }
}
