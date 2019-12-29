package com.rainz;

import com.rainz.Main.TreeNode;

public class ValidateBinarySearchTree {
    public static void test(String args[]) {
        TreeNode input1 = Main.TreeNode.buildTreeLevelOrder("2,1,3");
        //System.out.println(input1.toString());
        System.out.println(isValidBST(input1));
        TreeNode input2 = Main.TreeNode.buildTreeLevelOrder("5,1,4,null,null,3,6");
        //System.out.println(input2.toString());
        System.out.println(isValidBST(input2));
    }

    public static boolean helper(TreeNode root, int min, int max) {
        if (root == null)
            return true;
        if (root.val > max || root.val < min)
            return false;
        if (root.val == Integer.MIN_VALUE && root.left != null ||
                !helper(root.left, min, root.val-1))
            return false;
        if (root.val == Integer.MAX_VALUE && root.right != null ||
                !helper(root.right, root.val+1, max))
            return false;
        return true;
    }

    public static boolean isValidBST(TreeNode root) {
        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
