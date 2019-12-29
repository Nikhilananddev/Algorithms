package com.rainz;

import java.util.*;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree, return the vertical order traversal of its nodes values.
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 */


public class VerticalOrderTraversalofaBinaryTree {
    public static void test(String args[]) {
        TreeNode input1 = TreeNode.buildTreeLevelOrder("3,9,20,null,null,15,7");
        Main.printList2D(verticalTraversal(input1));
        TreeNode input2 = TreeNode.buildTreeLevelOrder("1,2,3,4,5,6,7");
        Main.printList2D(verticalTraversal(input2));
        TreeNode input3 = TreeNode.buildTreeLevelOrder("0,null,1,null,null,2,null");
        Main.printList2D(verticalTraversal(input3));
    }

    private static void helper(TreeNode node, int x, int y, Map<Integer, List<int[]>> xTable) {
        if (node == null)
            return;
        List<int[]> vals = xTable.get(x);
        if (vals == null) {
            vals = new ArrayList<>();
            xTable.put(x, vals);
        }
        int[] nodeVal = {y, node.val};
        vals.add(nodeVal);
        helper(node.left, x - 1, y - 1, xTable);
        helper(node.right, x + 1, y - 1, xTable);
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<int[]>> xTable = new TreeMap<>();
        helper(root, 0, 0, xTable);
        for (Map.Entry<Integer, List<int[]>> xEntry : xTable.entrySet()) {
            List<Integer> sameX = new ArrayList<>();
            ans.add(sameX);
            List<int[]> vals = xEntry.getValue();
            Collections.sort(vals, (x, y) -> x[0] != y[0] ? Integer.compare(y[0], x[0]): Integer.compare(x[1], y[1]));
            for (int[] val : vals) {
                sameX.add(val[1]);
            }
        }
        return ans;
    }
}