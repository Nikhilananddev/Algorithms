package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.*;

/*
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 */

public class BinaryTreeVerticalOrderTraversal {
    public static void test(String args[]) {
        Main.printList2D(verticalOrder(TreeNode.buildTree("3,9,20,null,null,15,7")));
        Main.printList2D(verticalOrder(TreeNode.buildTree("3,9,8,4,0,1,7")));
        Main.printList2D(verticalOrder(TreeNode.buildTree("3,9,8,4,0,1,7,null,null,null,2,5")));
    }

    private static void helper(TreeNode root, int pos, int level, Map<Integer, List<int[]>> treeMap) {
        if (root == null)
            return;
        List<int[]> list = treeMap.get(pos);
        if (list == null) {
            list = new ArrayList<>();
            treeMap.put(pos, list);
        }
        int[] rec = {root.val, level};
        list.add(rec);
        helper(root.left, pos-1, level+1, treeMap);
        helper(root.right, pos+1, level+1, treeMap);
    }

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer, List<int[]>> treeMap = new TreeMap<>();
        helper(root, 0, 0, treeMap);
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, List<int[]>> entry: treeMap.entrySet()) {
            List<Integer> col = new ArrayList<>();
            ans.add(col);
            // Sort by depths
            List<int[]> recs = new ArrayList<>();
            recs.addAll(entry.getValue());
            Collections.sort(recs, Comparator.comparingInt(x->x[1]));
            for (int[] rec: recs)
                col.add(rec[0]);
        }
        return ans;
    }
}
