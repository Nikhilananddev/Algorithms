package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a binary tree, determine if it is a complete binary tree.
 */
public class CheckCompletenessofaBinaryTree {
    public static void test(String args[]) {
        System.out.println(isCompleteTree(TreeNode.buildTree("1,2,3,4,5,6")));
        System.out.println(isCompleteTree(TreeNode.buildTree("1,2,3,4,5,null,7")));
    }

    public static boolean isCompleteTree(TreeNode root) {
        List<TreeNode> currLevel = new ArrayList<>();
        currLevel.add(root);
        boolean foundNull = false;
        while (!currLevel.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode n: currLevel) {
                if (foundNull && n != null)
                    return false;
                if (n == null)
                    foundNull = true;
                else {
                    if (n.right != null && n.left == null)
                        return false;
                    nextLevel.add(n.left);
                    nextLevel.add(n.right);
                }
            }
            currLevel = nextLevel;
        }
        return true;
    }
}
