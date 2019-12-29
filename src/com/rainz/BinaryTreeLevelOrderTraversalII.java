package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 */

public class BinaryTreeLevelOrderTraversalII {
    public static void test(String args[]) {
        TreeNode input1 = TreeNode.buildTreeLevelOrder("3,9,20,null,null,15,7");
        Main.printList2D(levelOrderBottom(input1));
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        /* Same as the first level order traversal problem, except we use a linked list
           and add each level at the begin, just to avoid a "reverse" and increase efficiency */
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null)
            return ans;
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        while (!level.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            List<Integer> nums = new ArrayList<>();
            for (TreeNode node: level)  {
                nums.add(node.val);
                if (node.left != null)
                    nextLevel.add(node.left);
                if (node.right != null)
                    nextLevel.add(node.right);
            }
            ans.add(0, nums); // add at the beginning
            level = nextLevel;
        }
        return ans;
    }
}
