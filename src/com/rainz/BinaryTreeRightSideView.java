package com.rainz;

import java.util.ArrayList;
import java.util.List;

import com.rainz.Main.TreeNode;

/*
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 */

public class BinaryTreeRightSideView {
    public static void test(String args[]) {
        Main.printList(rightSideView(TreeNode.buildTree("1,2,3,null,5,null,4")));
    }
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        while (!level.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode node: level)  {
                if (node.left != null)
                    nextLevel.add(node.left);
                if (node.right != null)
                    nextLevel.add(node.right);
            }
            ans.add(level.get(level.size()-1).val);
            level = nextLevel;
        }
        return ans;
    }
}
