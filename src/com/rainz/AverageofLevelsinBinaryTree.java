package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 */

public class AverageofLevelsinBinaryTree {
    public static void test(String args[]) {
        System.out.println(averageOfLevels(TreeNode.buildTree("3,9,20,null,null,15,7")));
    }


    // This can also be solved by level-order traversal with BFS

    private static void dfs(TreeNode root, int level, List<long[]> sums) {
        if (root == null)
            return;
        if (sums.size() <= level)
            sums.add(new long[2]);
        long[] rec = sums.get(level);
        rec[0] += root.val;
        ++rec[1];
        dfs(root.left, level+1, sums);
        dfs(root.right, level+1, sums);
    }
    public static List<Double> averageOfLevels(TreeNode root) {
        List<long[]> sums = new ArrayList<>();
        dfs(root, 0, sums);
        List<Double> ans = new ArrayList<>();
        for (long[] rec: sums)
            ans.add((double)rec[0]/rec[1]);
        return ans;
    }
}
