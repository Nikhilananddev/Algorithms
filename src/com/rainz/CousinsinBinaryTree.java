package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 */
public class CousinsinBinaryTree {
    public static void test(String args[]) {
        System.out.println(isCousins(TreeNode.buildTree("1,2,3,4"), 4, 3));
        System.out.println(isCousins(TreeNode.buildTree("1,2,3,null,4,null,5"), 5, 4));
        System.out.println(isCousins(TreeNode.buildTree("1,2,3,null,4"), 2, 3));
    }

    public static boolean isCousins(TreeNode root, int x, int y) {
        List<TreeNode> currLevel = new ArrayList<>();
        currLevel.add(root);
        int xLevel = -1, yLevel = -1;
        int level = 0;
        while (!currLevel.isEmpty() && (xLevel < 0 || yLevel < 0)) {
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode n: currLevel) {
                if (n == null)
                    continue;
                if (n.val == x)
                    xLevel = level;
                else if (n.val == y)
                    yLevel = level;
                if (n.left != null && n.right != null) {
                    if (n.left.val == x && n.right.val == y ||
                        n.left.val == y && n.right.val == x)
                        return false;
                }
                nextLevel.add(n.left);
                nextLevel.add(n.right);
            }
            ++level;
            currLevel = nextLevel;
        }
        return xLevel == yLevel;
    }
}
