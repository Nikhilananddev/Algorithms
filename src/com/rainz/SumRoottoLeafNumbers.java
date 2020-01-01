package com.rainz;

import com.rainz.Main.TreeNode;

public class SumRoottoLeafNumbers {
    public static void test(String args[]) {
        System.out.println(sumNumbers(TreeNode.buildTree("1,2,3")));
        System.out.println(sumNumbers(TreeNode.buildTree("4,9,0,5,1")));
    }

    private static int helper(TreeNode node, int number, int sum) {
        if (node == null)
            return sum;
        number = 10*number + node.val;
        if (node.left == null && node.right == null) {
            sum += number;
            return sum;
        }
        sum = helper(node.left, number, sum);
        sum = helper(node.right, number, sum);
        return sum;
    }
    public static int sumNumbers(TreeNode root) {
        return helper(root, 0, 0);
    }
}
