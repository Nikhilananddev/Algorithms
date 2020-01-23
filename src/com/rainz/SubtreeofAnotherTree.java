package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 */
public class SubtreeofAnotherTree {
    public static void test(String args[]) {
        System.out.println(isSubtree(TreeNode.buildTree("3,4,5,1,2"), TreeNode.buildTree("4,1,2")));
        System.out.println(isSubtree(TreeNode.buildTree("3,4,5,1,2,null,null,null,null,0"), TreeNode.buildTree("4,1,2")));
    }

    private static boolean sameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null)
            return t1 == t2;
        if (t1.val != t2.val)
            return false;
        return sameTree(t1.left, t2.left) && sameTree(t1.right, t2.right);
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)
            return t == null;
        if (sameTree(s, t))
            return true;
        return (isSubtree(s.left, t) || isSubtree(s.right, t));
    }
}
