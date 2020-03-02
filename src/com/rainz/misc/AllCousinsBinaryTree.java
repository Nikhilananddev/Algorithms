package com.rainz.misc;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a binary tree and a node, print all cousins of given node. Note that siblings should not be printed.
 */

public class AllCousinsBinaryTree {
    public static void test(String args[]) {
        TreeNode t1 = TreeNode.buildTree("1,2,3,4,5,6,7,8,9,10,11");
        TreeNode n1 = TreeNode.findFirst(t1, 8);
        List<TreeNode> ans1 = printCousins(t1, n1);
        for (TreeNode n: ans1)
            System.out.print(n.val+", ");
        System.out.println();


        TreeNode t2 = TreeNode.buildTree("1,2,3,4,5,null,7,8,9,10,null,12,13,14,null");
        TreeNode n2 = TreeNode.findFirst(t2, 8);
        List<TreeNode> ans2 = printCousins(t2, n2);
        for (TreeNode n: ans2)
            System.out.print(n.val+", ");
        System.out.println();
    }

    public static List<TreeNode> printCousins(TreeNode root, TreeNode node) {
        return printCousinsBFS(root, node);
    }

    /*
     * Below is BFS/level order solution.
     * Can also be done via DFS, but you have to get the level of the node first.
     */
    public static List<TreeNode> printCousinsBFS(TreeNode root, TreeNode node) {
        List<TreeNode> ans = new ArrayList<>();
        List<TreeNode> currLevel = new ArrayList<>();
        currLevel.add(root);
        TreeNode sibling = null;
        boolean found = false;
        while (!currLevel.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode n: currLevel) {
                if (n == null)
                    continue;
                if (n.left == node) {
                    found = true;
                    sibling = n.right;
                } else if (n.right == node) {
                    found = true;
                    sibling = n.left;
                }
                nextLevel.add(n.left);
                nextLevel.add(n.right);
            }
            if (found) {
                for (TreeNode csn: nextLevel) {
                    if (csn != node && csn != sibling && csn != null)
                        ans.add(csn);
                }
                break;
            }
            currLevel = nextLevel;
        }
        return ans;
    }
}
