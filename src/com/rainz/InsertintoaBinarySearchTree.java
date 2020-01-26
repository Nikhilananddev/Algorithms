package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 * Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
 */
public class InsertintoaBinarySearchTree {
    public static void test(String args[]) {
        System.out.println(insertIntoBST(TreeNode.buildTree("4,2,7,1,3"), 5));
    }
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode nodeNew = new TreeNode(val);
        if (root == null)
            return nodeNew;
        TreeNode curr = root;
        TreeNode parent = null;
        while (curr != null) {
            parent = curr;
            if (val < curr.val) {
                if (curr.left == null) {
                    curr.left = nodeNew;
                    break;
                } else {
                    curr = curr.left;
                }
            } else if (val > curr.val) {
                if (curr.right == null) {
                    curr.right = nodeNew;
                    break;
                } else {
                    curr = curr.right;
                }
            } else
                break; // value already exists

        }
        return root;
    }
}
