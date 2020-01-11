package com.rainz;

import com.rainz.Main.TreeNode;
/*
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.
 */
public class TwoSumIVInputisaBST {
    public static void test(String args[]) {
        TreeNode input1 = TreeNode.buildTree("5,3,6,2,4,null,7");
        System.out.println(findTarget(input1, 9));
        TreeNode input2 = TreeNode.buildTree("5,3,6,2,4,null,7");
        System.out.println(findTarget(input2, 28));
        TreeNode input3 = TreeNode.buildTree("1");
        System.out.println(findTarget(input3, 2));

    }

    private static boolean findNum(TreeNode root, int num) {
        if (root == null)
            return false;
        if (num == root.val)
            return true;
        if (num < root.val)
            return findNum(root.left, num);
        return findNum(root.right, num);
    }

    private static boolean helper(TreeNode node, TreeNode root, int k) {
        if (node == null)
            return false;
        if (helper(node.left, root, k))
            return true;
        // Check if k is twice of node.val to prevent findNum from finding itself
        if (k != node.val*2 && findNum(root, k-node.val))
            return true;
        return helper(node.right, root, k);
    }

    public static boolean findTarget(TreeNode root, int k) {
        return helper(root, root, k);
    }
}
