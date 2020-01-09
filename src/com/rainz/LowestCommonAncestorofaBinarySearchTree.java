package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 */

public class LowestCommonAncestorofaBinarySearchTree {
    public static void test(String args[]) {
        TreeNode root1 = TreeNode.buildTree("6,2,8,0,4,7,9,null,null,3,5");
        TreeNode p1 = TreeNode.findFirst(root1, 2);
        TreeNode q1 = TreeNode.findFirst(root1, 8);
        System.out.println(lowestCommonAncestor(root1, p1, q1).val);
        TreeNode root2 = TreeNode.buildTree("6,2,8,0,4,7,9,null,null,3,5");
        TreeNode p2 = TreeNode.findFirst(root2, 2);
        TreeNode q2 = TreeNode.findFirst(root2, 4);
        System.out.println(lowestCommonAncestor(root2, p2, q2).val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while (curr != null) {
            if (p.val < curr.val && q.val < curr.val)
                curr = curr.left;
            else if (p.val > curr.val && q.val > curr.val)
                curr = curr.right;
            else
                return curr;
        }
        return curr;
    }
}
