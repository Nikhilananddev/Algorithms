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

    // DFS solution
    private static void helper(TreeNode root, TreeNode parent, int x, int y, int level, int[] levels, TreeNode[] parents) {
        if (root == null)
            return;
        if (root.val == x) {
            parents[0] = parent;
            levels[0] = level;
        } else if (root.val == y) {
            parents[1] = parent;
            levels[1] = level;
        }
        if (levels[0] >= 0 && levels[1] >= 0) {
            return;
        }
        helper(root.left, root,  x, y, level+1, levels, parents);
        helper(root.right, root,  x, y, level+1, levels, parents);
    }

    public static boolean isCousins(TreeNode root, int x, int y) {
        int[] levels = { -1, -2 };
        TreeNode[] parents = { null, null };
        helper(root, null, x, y, 0, levels, parents);
        return (levels[0] == levels[1] && parents[0] != parents[1]);
    }

    // BFS/level order solution
    public static boolean isCousinsBFS(TreeNode root, int x, int y) {
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
