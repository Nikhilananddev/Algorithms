package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 */
public class ClosestBinarySearchTreeValue {
    public static void test(String args[]) {
        System.out.println(closestValue(TreeNode.buildTree("4,2,5,1,3"), 3.714286));
    }
    public static int closestValue(TreeNode root, double target) {
        int ans = root.val;
        double diff = Math.abs(root.val - target);
        if (diff == 0)
            return ans;
        if (target < root.val && root.left != null) {
            int lRes = closestValue(root.left, target);
            double lDiff = Math.abs(lRes - target);
            if (lDiff < diff) {
                diff = lDiff;
                ans = lRes;
            }
        }
        if (target > root.val && root.right != null) {
            int rRes = closestValue(root.right, target);
            double rDiff = Math.abs(rRes - target);
            if (rDiff < diff)
                ans = rRes;
        }
        return ans;
    }
}
