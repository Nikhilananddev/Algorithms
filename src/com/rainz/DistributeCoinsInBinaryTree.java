package com.rainz;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.rainz.Main.TreeNode;

/*
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)
 * Return the number of moves required to make every node have exactly one coin
 */
public class DistributeCoinsInBinaryTree {

    public static void test(String args[]) {
        TreeNode n0 = new TreeNode(1 );
        TreeNode n00 = new TreeNode(0 );
        TreeNode n01 = new TreeNode(0 );
        TreeNode n001 = new TreeNode(3 );
        n0.left = n00;
        n0.right = n01;
        n01.right = n001;
        System.out.println(distributeCoinsInBinaryTree(n0));
    }

    /*
    Another simple solution is simply use recursion to compute the net balance of left & right subtrees.
    Then add the absolute value of the balance to total moves.
     */

    public static int distributeCoinsInBinaryTree(TreeNode root) {
        final int TARGET = 1;
        int result = 0;
        Stack<List<TreeNode>> stk = new Stack<>();
        List<TreeNode> currLevel = new ArrayList<>();
        currLevel.add(root); // this node
        currLevel.add(null); // parent
        while (!currLevel.isEmpty()) {
            stk.push(currLevel);
            List<TreeNode> nextLevel = new ArrayList<>();
            for (int i = 0; i < currLevel.size(); i += 2) {
                TreeNode node = currLevel.get(i);
                if (node.left != null) {
                    nextLevel.add(node.left);
                    nextLevel.add(node); // parent
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                    nextLevel.add(node); // parent
                }
            }
            currLevel = nextLevel;
        }
        while (!stk.isEmpty()) {
            List<TreeNode> level = stk.pop();
            for (int i = 0; i < level.size(); i+=2) {
                TreeNode node = level.get(i);
                TreeNode parent = level.get(i+1);
                if (parent != null) {
                    int steps = node.val - TARGET;
                    parent.val += steps;
                    node.val = TARGET;
                    if (steps < 0)
                        steps = -steps;
                    result += steps;
                }
            }
        }

        return result;
    }
}
