package com.rainz;

import com.rainz.Main.TreeNode;

public class PathSum {
    public static void test(String args[]) {
        TreeNode input = Main.TreeNode.buildTreeLevelOrder("5,4,8,11,null,13,4,7,2,null,null,null,null,null,1");
        System.out.println(hasPathSum(input, 22));
        TreeNode input2 = Main.TreeNode.buildTreeLevelOrder("1,2,null");
        System.out.println(hasPathSum(input2, 1));
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        int newSum = sum - root.val;
        if (root.left == null && root.right == null)
            return newSum == 0;
        return hasPathSum(root.left, newSum) || hasPathSum(root.right, newSum);
    }
}
