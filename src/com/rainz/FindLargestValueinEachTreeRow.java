package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * You need to find the largest value in each row of a binary tree.
 */

public class FindLargestValueinEachTreeRow {
    public static void test(String args[]) {
        System.out.println(largestValues(TreeNode.buildTree("1,3,2,5,3,null,9")));
    }

    private static void dfs(TreeNode root, int level, List<Integer> ans) {
        if (root == null)
            return;
        if (level >= ans.size())
            ans.add(root.val);
        else if (ans.get(level) < root.val)
            ans.set(level, root.val);
        dfs(root.left, level+1, ans);
        dfs(root.right, level+1, ans);
    }

    // Can be done with ether BFS (level-order traversal) or DFS
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }
}
