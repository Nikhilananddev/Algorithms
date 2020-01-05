package com.rainz;

import java.util.ArrayList;
import java.util.List;

import com.rainz.Main.TreeNode;

public class AllNodesDistanceKinBinaryTree {
    public static void test(String args[]) {
        TreeNode root = TreeNode.buildTree("3,5,1,6,2,0,8,null,null,7,4");
        TreeNode target = TreeNode.findFirst(root, 5);
        Main.printList(distanceK(root, target, 2));
    }

    // We already located "target", just find nodes with distance=K
    private static void findDistK(TreeNode root, int K, List<Integer> ans) {
        if (root == null)
            return;
        if (K == 0) {
            ans.add(root.val);
            return; // no need to go down further;
        }
        findDistK(root.left, K-1, ans);
        findDistK(root.right, K-1, ans);
    }

    // Return -1 if target is not under root, or distance if it is
    private static int helper(TreeNode root, TreeNode target, int K, List<Integer> ans) {
        if (root == null)
            return -1;
        int result = -1;
        if (root == target) {
            findDistK(root, K, ans);
            return 0; // distance from target
        }
        int leftTreeDist = helper(root.left, target, K, ans);
        if (leftTreeDist >= 0) {
            // target is in left tree
            result = leftTreeDist + 1;
            if (result == K) {
                ans.add(root.val);
            } else if (result < K) {
                findDistK(root.right, K-result-1, ans);
            }
        } else {
            int rightTreeDist = helper(root.right, target, K, ans);
            if (rightTreeDist >= 0) {
                // target is in right tree
                result = rightTreeDist + 1;
                if (result == K) {
                    ans.add(root.val);
                } else if (result < K) {
                    findDistK(root.left, K - result - 1, ans);
                }
            }
        }

        return result;
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();
        helper(root, target, K, ans);
        return ans;
    }
}
