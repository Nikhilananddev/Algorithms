package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Two players play a turn based game on a binary tree.  We are given the root of this binary tree, and the number of nodes n in the tree.  n is odd, and each node has a distinct value from 1 to n.
 * Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x.  The first player colors the node with value x red, and the second player colors the node with value y blue.
 * Then, the players take turns starting with the first player.  In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)
 * If (and only if) a player cannot choose such a node in this way, they must pass their turn.  If both players pass their turn, the game ends, and the winner is the player that colored more nodes.
 * You are the second player.  If it is possible to choose such a y to ensure you win the game, return true.  If it is not possible, return false.
 */

public class BinaryTreeColoringGame {
    public static void test(String args[]) {
        System.out.println(btreeGameWinningMove(TreeNode.buildTree("1,2,3,4,5,6,7,8,9,10,11"), 11, 3));
        System.out.println(btreeGameWinningMove(TreeNode.buildTree("1,2,3,4,5,6,7,8,9"), 9, 2));
    }

    /*
     * If you color parent of x, you own the entire tree except the x subtree
     * If you color left/right of x, you own the entire left/right subtree
     * After making initial pick, you'll no longer be able to color outside of these areas
     * because coloring a node "cuts off" its subtree from the rest of tree
     */

    private static int findChildCount(TreeNode root, int[] counts, int x, TreeNode[] xNd) {
        if (root == null)
            return 0;
        if (root.val == x)
            xNd[0] = root;
        int count = 1;
        count += findChildCount(root.left, counts, x, xNd);
        count += findChildCount(root.right, counts, x, xNd);
        counts[root.val] = count;
        return count;
    }

    public static boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        int[] counts = new int[n+1];
        TreeNode[] xNd = {null}; // Node for x
        findChildCount(root, counts, x, xNd);
        TreeNode xNode = xNd[0];
        int total = counts[root.val];
        int parent = total - counts[x];
        if (parent > counts[x])
            return true;
        int left = xNode.left != null ? counts[xNode.left.val] : 0;
        if (left > total - left)
            return true;
        int right = xNode.right != null ? counts[xNode.right.val] : 0;
        if (right > total - right)
            return true;
        return false;
    }
}
