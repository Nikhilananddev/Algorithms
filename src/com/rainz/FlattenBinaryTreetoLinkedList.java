package com.rainz;

import com.rainz.Main.TreeNode;

public class FlattenBinaryTreetoLinkedList {
    public static void test(String args[]) {
        TreeNode root = TreeNode.buildTree("1,2,5,3,4,null,6");
        flatten(root);
        TreeNode root2 = TreeNode.buildTree("1,2,null,3");
        flatten(root2);
        System.out.println(root2);
    }

    // Returns tail of the subtree. Head of the tree is same as input node.
    private static TreeNode helper(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        TreeNode tailLeft = helper(left);
        TreeNode tailRight = helper(right);
        if (tailLeft != null) {
            root.right = left;
            tailLeft.right = right;
        }
        // Output is in prefix order, so for tail, we first check right, then left, finally root
        if (tailRight != null)
            return tailRight;
        else if (tailLeft != null)
            return tailLeft;
        else
            return root;
    }

    public static void flatten(TreeNode root) {
        helper(root);
    }
}
