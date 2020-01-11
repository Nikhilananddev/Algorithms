package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Return the root node of a binary search tree that matches the given preorder traversal.
 */

public class ConstructBinarySearchTreefromPreorderTraversal {
    public static void test(String args[]) {
        int[] input1 = {8,5,1,7,10,12};
        System.out.println(bstFromPreorder(input1));
    }

    private static TreeNode helper(int[] preorder, int start, int end) {
        if (start >= end)
            return null;
        TreeNode root = new TreeNode(preorder[start]);
        int rightStart = start + 1;
        while (rightStart < end && preorder[rightStart] < root.val)
            ++rightStart;
        root.left = helper(preorder, start+1, rightStart);
        root.right = helper(preorder, rightStart, end);
        return root;
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length);
    }
}
