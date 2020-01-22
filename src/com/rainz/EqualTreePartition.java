package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.
 */
public class EqualTreePartition {
    public static void test(String args[]) {
        System.out.println(checkEqualTree(TreeNode.buildTree("5,10,10,null,null,2,3")));
        System.out.println(checkEqualTree(TreeNode.buildTree("1,2,10,null,null,2,10")));
        System.out.println(checkEqualTree(TreeNode.buildTree("0,-1,1")));
    }

    public static int helper(TreeNode root, Map<Integer, TreeNode> sumTbl) {
        int left = root.left != null ? (helper(root.left, sumTbl)) : 0;
        int right = root.right != null ? (helper(root.right, sumTbl)) : 0;
        int sum = root.val + left + right;
        if (sumTbl.get(sum) == null)
            sumTbl.put(sum, root); // only record the lowest root. Prevents cases like root sum=0 and a subtree sum=0
        return sum;
    }

    public static boolean checkEqualTree(TreeNode root) {
        if (root == null)
            return false;
        Map<Integer, TreeNode> sumTbl = new HashMap<>();
        int rootSum = helper(root, sumTbl);
        if (rootSum % 2 != 0)
            return false;
        TreeNode subTree = sumTbl.get(rootSum/2);
        // Note: take care of the case where sum for root is 0
        return subTree != null && subTree != root;
    }
}
