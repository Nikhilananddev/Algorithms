package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 */

public class MaximumLevelSumofaBinaryTree {
    public static void test(String args[]) {
        TreeNode input1 = TreeNode.buildTree("1,7,0,7,-8,null,null");
        System.out.println(maxLevelSum(input1));
    }

    public static int maxLevelSum(TreeNode root) {
        if (root == null)
            return 0;
        int ans = 0;
        int maxSum = Integer.MIN_VALUE;
        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        int level = 0;
        while (!curr.isEmpty()) {
            ++level;
            List<TreeNode> next = new ArrayList<>();
            int sum = 0;
            for (TreeNode n: curr) {
                if (n == null)
                    continue;
                sum += n.val;
                next.add(n.left);
                next.add(n.right);
            }
            if (sum > maxSum) {
                maxSum = sum;
                ans = level;
            }
            curr = next;
        }
        return ans;
    }
}
