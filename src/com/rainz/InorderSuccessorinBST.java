package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.Stack;

/*
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val.
 */
public class InorderSuccessorinBST {
    public static void test(String args[]) {
        TreeNode t1 = TreeNode.buildTree("2,1,3");
        TreeNode p1 = TreeNode.findFirst(t1, 1);
        TreeNode result = inorderSuccessor(t1, p1);
        if (result != null)
            System.out.println(result.val);
        else
            System.out.println("null");
        TreeNode t2 = TreeNode.buildTree("5,3,6,2,4,null,null,1");
        TreeNode p2 = TreeNode.findFirst(t2, 6);
        result = inorderSuccessor(t2, p2);
        if (result != null)
            System.out.println(result.val);
        else
            System.out.println("null");
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode greaterParent = null;
        TreeNode node = root;
        while (node != null) {
            if (node == p)
                break;
            if (p.val < node.val) {
                greaterParent = node; // save this because it's greater than p
                node = node.left;
            } else {
                // Don't save node as greaterParent because it's less than p
                node = node.right;
            }
        }
        if (node.right == null)
            return greaterParent;
        TreeNode prev = null;
        node = node.right;
        while (node != null) {
            prev = node;
            node = node.left;
        }
        return prev;
    }
}
