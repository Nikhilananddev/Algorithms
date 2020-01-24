package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree root and an integer target, delete all the leaf nodes with value target.
 * Note that once you delete a leaf node with value target, if it's parent node becomes a leaf node and has the value target, it should also be deleted (you need to continue doing that until you can't).
 */
public class DeleteLeavesWithaGivenValue {
    public static void test(String args[]) {
        System.out.println(removeLeafNodes(TreeNode.buildTree("1,2,3,2,null,2,4"), 2));
        System.out.println(removeLeafNodes(TreeNode.buildTree("1,3,3,3,2"), 3));
        System.out.println(removeLeafNodes(TreeNode.buildTree("1,2,null,2,null,2"), 2));
        System.out.println(removeLeafNodes(TreeNode.buildTree("1,1,1"), 1));
        System.out.println(removeLeafNodes(TreeNode.buildTree("1,2,3"), 1));
    }
    public static TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null)
            return null;
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.val == target && root.left == null && root.right == null)
            return null;
        return root;
    }

}
