package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.*;

/*
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 * Note:
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 */

public class ClosestBinarySearchTreeValueII {
    public static void test(String args[]) {
        System.out.println(closestKValues(TreeNode.buildTree("4,2,5,1,3"), 3.714286, 2));
    }

    /*
     * We do in-order traversal. So all nodes traversed will be in sorted order, so are vals in the k-element window
     * Case 1: new node is closer than start of window, start of window must be furthest away from target, so remove it.
     * In this case target is either greater than end of window, or within window but closer to end.
     * Case 2: otherwise, we are "moving away" from target, so no need to try further. Just return the result.
     * In this case target is either less than start of window, or within window but closer to start.
     */
    private static void helper(TreeNode root, double target, int k, Queue<Integer> closest) {
        if (root == null)
            return;
        helper(root.left, target, k, closest);
        if (closest.size() < k)
            closest.add(root.val);
        else if (Math.abs(root.val - target) < Math.abs(closest.peek() - target)) {
            closest.remove(); // remove first element
            closest.add(root.val); // add to the back
        } else
            return;
        helper(root.right, target, k, closest);
    }

    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        Queue<Integer> closest = new LinkedList<>();
        helper(root, target, k, closest);
        List<Integer> ans = new ArrayList<>();
        ans.addAll(closest);
        return ans;
    }
}
