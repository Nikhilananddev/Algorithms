package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.
 */
public class TwoSumBSTs {
    public static void test(String args[]) {
        System.out.println(twoSumBSTs(TreeNode.buildTree("2,1,4"), TreeNode.buildTree("1,0,3"), 5));
        System.out.println(twoSumBSTs(TreeNode.buildTree("0,-10,10"), TreeNode.buildTree("5,1,7,0,2"), 18));
    }

    public static boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null)
            return false;
        int sum = root1.val + root2.val;
        if (sum == target)
            return true;
        if (sum < target)
            return twoSumBSTs(root1.right, root2, target) || twoSumBSTs(root1, root2.right, target);
        return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1, root2.left, target);
    }
}
