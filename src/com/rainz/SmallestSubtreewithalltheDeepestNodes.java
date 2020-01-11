package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
 * A node is deepest if it has the largest depth possible among any node in the entire tree.
 * The subtree of a node is that node, plus the set of all descendants of that node.
 * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
 */
public class SmallestSubtreewithalltheDeepestNodes {
    public static void test(String args[]) {
        TreeNode root1 = TreeNode.buildTree("3,5,1,6,2,0,8,null,null,7,4");
        System.out.println(subtreeWithAllDeepest(root1));
    }

    private static class HelpObj{
        public TreeNode node; // deepest common ancestor
        public int depth; // depth of deepest leave
        HelpObj(TreeNode n, int d) {
            node = n;
            depth = d;
        }
    }

    /*
     * Main idea is, if max depths from both left and right are equal, return this node;
     * Else return max of left and right.
     */
    private static HelpObj helper(TreeNode root, int depth) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null)
            return new HelpObj(root, depth);
        int depthLeft = 0;
        HelpObj left = helper(root.left, depth + 1);
        if (left != null)
            depthLeft = left.depth;
        int depthRight = 0;
        HelpObj right = helper(root.right, depth + 1);
        if (right != null)
            depthRight = right.depth;
        if (depthLeft == depthRight) {
            // Return this node, which includes both subtrees
            return new HelpObj(root, depthLeft);
        }
        return depthLeft > depthRight ? left : right;
    }

    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null)
            return null;
        return helper(root, 0).node;
    }
}
