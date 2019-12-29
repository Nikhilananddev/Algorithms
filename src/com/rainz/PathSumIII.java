package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 */

public class PathSumIII {
    public static void test(String args[]) {
        TreeNode input = Main.TreeNode.buildTreeLevelOrder("10,5,-3,3,2,null,11,3,-2,null,1,null,null,null,null");
        System.out.println(pathSum(input, 8));
        TreeNode input2 = Main.TreeNode.buildTreeLevelOrder("5,4,8,11,null,13,4,7,2,null,null,5,1,null,null");
        System.out.println(pathSum(input2, 22));
    }

    private static int helper(TreeNode root, int target, int parentsSum, Map<Integer, Integer> sumsTable, int ans) {
        if (root == null)
            return ans;
        int newSum = root.val + parentsSum;
        Integer count = sumsTable.get(newSum-target);
        if (count != null)
            ans += count;
        Integer newCount = sumsTable.get(newSum);
        if (newCount == null)
            newCount = 0;
        sumsTable.put(newSum, newCount+1);
        ans = helper(root.left, target, newSum, sumsTable, ans);
        ans = helper(root.right, target, newSum, sumsTable, ans);
        sumsTable.put(newSum, newCount); // restore count of current sum
        return ans;
    }

    public static int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> sumsTable = new HashMap<>();
        sumsTable.put(0, 1); // don't forget to put this
        return helper(root, sum, 0, sumsTable, 0);
    }
}
