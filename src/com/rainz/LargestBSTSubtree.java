package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
 */
public class LargestBSTSubtree {
    public static void test(String args[]) {
        System.out.println(largestBSTSubtree(TreeNode.buildTree("10,5,15,1,8,null,7")));
        System.out.println(largestBSTSubtree(TreeNode.buildTree("4,2,7,2,3,5,null,2,null,null,null,null,null,1")));
    }

    /*
     * This is a bottom-up approach. We call helper on every subtrees.
     * Subtrees tell us whether it's a BST and if so, what is the range.
     */
    public static int[] helper(TreeNode root, int[] maxTree) {
        if (root == null)
            return null;
        int[] res = {1, root.val, root.val}; // Size if BST (0 otherwise), min, max; min/max valid only if BST
        int[] left = helper(root.left, maxTree);
        if (left != null) {
            if (left[0] == 0)
                res[0] = 0; // left not BST
            else
                res[1] = left[1]; // update min
        }
        int[] right = helper(root.right, maxTree);
        if (right != null) {
            if (right[0] == 0)
                res[0] = 0; // right not BST
            else
                res[2] = right[2]; // update max
        }
        if (res[0] > 0) {
            // If we get here, left & right are BSTs
            if (root.val == Integer.MIN_VALUE && root.left != null ||
                    root.val == Integer.MAX_VALUE && root.right != null) {
                // Special handling for MIN_VALUE & MAX_VALUE.
                res[0] = 0; // root is MIN/MAX and there are more on left/right, not BST
            }
            else if ( (left != null && root.val <= left[2]) ||
                      (right != null && root.val >= right[1]) ) {
                // Root.val is not in between left & right trees, not BST
                res[0] = 0;
            } else {
                // This is a BST. Add sizes from left & right
                int leftSize = left == null ? 0 : left[0];
                int rightSize = right == null ? 0 : right[0];
                res[0] += leftSize + rightSize;
                if (res[0] > maxTree[0])
                    maxTree[0] = res[0];
            }
        }
        return res;
    }

    public static int largestBSTSubtree(TreeNode root) {
        int[] ans = {0};
        helper(root, ans);
        return ans[0];
    }
}
