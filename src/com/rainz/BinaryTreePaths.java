package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a binary tree, return all root-to-leaf paths.
 */
public class BinaryTreePaths {
    public static void test(String args[]) {
        System.out.println(binaryTreePaths(TreeNode.buildTree("1,2,3,null,5")));
    }

    private static void helper(TreeNode root, List<String> path, List<String> ans) {
        if (root == null)
            return;
        path.add(Integer.toString(root.val));
        if (root.left == null && root.right == null) {
            ans.add(String.join("->", path));
        } else {
            helper(root.left, path, ans);
            helper(root.right, path, ans);
        }
        path.remove(path.size()-1);
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        helper(root, path, ans);
        return ans;
    }
}
