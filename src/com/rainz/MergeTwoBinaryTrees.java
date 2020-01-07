package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
 */

public class MergeTwoBinaryTrees {
    public static void test(String args[]) {
        TreeNode a1 = TreeNode.buildTree("1,3,2,5");
        TreeNode b1 = TreeNode.buildTree("2,1,3,null,4,null,7");
        System.out.println(mergeTrees(a1, b1));
    }


    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return null;
        int sum = 0;
        if (t1 != null)
            sum += t1.val;
        if (t2 != null)
            sum += t2.val;
        TreeNode node = new TreeNode(sum);
        TreeNode t1Left = t1 != null ? t1.left : null;
        TreeNode t1Right = t1 != null ? t1.right : null;
        TreeNode t2Left = t2 != null ? t2.left : null;
        TreeNode t2Right = t2 != null ? t2.right : null;
        node.left = mergeTrees(t1Left, t2Left);
        node.right = mergeTrees(t1Right, t2Right);
        return node;
    }
}
