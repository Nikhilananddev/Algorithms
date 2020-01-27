package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 */
public class FindLeavesofBinaryTree {
    public static void test(String args[]) {
        Main.printList2D(findLeaves(TreeNode.buildTree("1,2,3,4,5")));
    }

    private static int helper(TreeNode root, List<List<Integer>> ans) {
        if (root == null)
            return -1; // one level below leaf
        int lLevel = helper(root.left, ans);
        int rLevel = helper(root.right, ans);
        int level = 1 + Math.max(lLevel,rLevel);
        while (ans.size() <= level)
            ans.add(new ArrayList<>());
        ans.get(level).add(root.val);
        return level;
    }

    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }
}
