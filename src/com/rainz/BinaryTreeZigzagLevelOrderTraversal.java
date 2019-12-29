package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreeZigzagLevelOrderTraversal {
    public static void test(String args[]) {
        TreeNode input1 = TreeNode.buildTreeLevelOrder("3,9,20,null,null,15,7");
        Main.printList2D(zigzagLevelOrder(input1));
    }


    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        boolean isReversed = false;
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
            if (isReversed)
                Collections.reverse(nums);
            ans.add(nums);
            level = nextLevel;
            isReversed = !isReversed;
        }
        return ans;
    }
}
