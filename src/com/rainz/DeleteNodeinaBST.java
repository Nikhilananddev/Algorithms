package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 */

public class DeleteNodeinaBST {
    public static void test(String args[]) {
        System.out.println(deleteNode(TreeNode.buildTree("5,3,6,2,4,null,7"), 3));
        System.out.println(deleteNode(TreeNode.buildTree("5,3,6,2,4,null,7"), 0));
        System.out.println(deleteNode(TreeNode.buildTree("5,3,6,2,4,null,7"), 5));
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        TreeNode retNode = root;
        TreeNode node = root;
        TreeNode parent = null;
        while (node != null) {
            if (key < node.val) {
                parent = node;
                node = node.left;
                continue;
            }
            if (key > node.val) {
                parent = node;
                node = node.right;
                continue;
            }
            // Found node
            TreeNode promote;
            if (node.left != null) {
                promote = node.left;
                // Attache right tree to max of left tree
                TreeNode curr = node.left;
                while (curr.right != null)
                    curr = curr.right;
                curr.right = node.right;
            } else
                promote = node.right;
            if (node == root)
                retNode = promote;
            if (parent != null) {
                if (node == parent.left)
                    parent.left = promote;
                else
                    parent.right = promote;
            }
            break;
        }
        return retNode;
    }
}
