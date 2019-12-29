package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 */

public class HouseRobberIII {
    public static void test(String args[]) {
        TreeNode input = TreeNode.buildTree("3,2,3,null,3,null,1");
        System.out.println(rob(input));
    }

    private static int helper(TreeNode node, Map<TreeNode, Integer> cache) {
        if (node == null)
            return 0;
        Integer cachedVal = cache.get(node);
        if (cachedVal != null)
            return cachedVal;
        // Case 1: rob current node
        int robVal = node.val;
        if (node.left != null) {
            robVal += helper(node.left.left, cache);
            robVal += helper(node.left.right, cache);
        }
        if (node.right != null) {
            robVal += helper(node.right.left, cache);
            robVal += helper(node.right.right, cache);
        }
        // case 2: not rob current node
        int notRobVal = helper(node.left, cache) + helper(node.right, cache);
        int result = Math.max(robVal, notRobVal);
        cache.put(node, result);
        return result;
    }

    public static int rob(TreeNode root) {
        Map<TreeNode, Integer> cache = new HashMap<>();
        return helper(root, cache);
    }
}
