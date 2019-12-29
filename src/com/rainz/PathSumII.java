package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    public static void test(String args[]) {
        TreeNode input = Main.TreeNode.buildTreeLevelOrder("5,4,8,11,null,13,4,7,2,null,null,null,null,null,1");
        Main.printList2D(pathSum(input, 22));
        TreeNode input2 = Main.TreeNode.buildTreeLevelOrder("1,2,null");
        Main.printList2D(pathSum(input2, 1));
    }

    public static void helper(TreeNode root, int sum, List<Integer> path, List<List<Integer>> ans) {
        if (root == null)
            return;
        path.add(root.val);
        int newSum = sum - root.val;
        if (root.left == null && root.right == null) {
            if (newSum == 0) {
                List<Integer> sol = new ArrayList<>();
                sol.addAll(path);
                ans.add(sol);
            }
        } else {
            helper(root.left, newSum, path, ans);
            helper(root.right, newSum, path, ans);
        }
        path.remove(path.size()-1);
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        helper(root, sum, path, ans);
        return ans;
    }
}
