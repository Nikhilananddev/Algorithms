package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class MinimumDepthofBinaryTree {
    public static void test(String args[]) {
        System.out.println(minDepth(TreeNode.buildTree("3,9,20,null,null,15,7")));
    }

    public static int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        int ans = 1;
        while (!curr.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode n: curr) {
                if (n.left == null && n.right == null)
                    return ans;
                if (n.left != null)
                    next.add(n.left);
                if (n.right != null)
                    next.add(n.right);
            }
            ++ans;
            curr = next;
        }
        return ans;
    }
}
