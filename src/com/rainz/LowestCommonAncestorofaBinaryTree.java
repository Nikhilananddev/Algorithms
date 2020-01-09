package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 */

public class LowestCommonAncestorofaBinaryTree {
    public static void test(String args[]) {
        Main.TreeNode root1 = Main.TreeNode.buildTree("3,5,1,6,2,0,8,null,null,7,4");
        Main.TreeNode p1 = Main.TreeNode.findFirst(root1, 5);
        Main.TreeNode q1 = Main.TreeNode.findFirst(root1, 1);
        System.out.println(lowestCommonAncestor(root1, p1, q1).val);
        Main.TreeNode root2= Main.TreeNode.buildTree("3,5,1,6,2,0,8,null,null,7,4");
        Main.TreeNode p2 = Main.TreeNode.findFirst(root2, 5);
        Main.TreeNode q2 = Main.TreeNode.findFirst(root2, 4);
        System.out.println(lowestCommonAncestor(root2, p2, q2).val);
    }

    /*
     * Return 2 if both p & q are under root, 1 if one of them is, or 0 if neither.
     * When both children returns < 2 but root returns 2, root is the LCA. Add it to ans.
     */
    private static int helper(TreeNode root, TreeNode p, TreeNode q, List<TreeNode> ans) {
        if (root == null)
            return 0;
        int sum = 0;
        if (root.val == p.val || root.val == q.val)
            ++sum;
        int leftSum = helper(root.left, p, q, ans);
        int rightSum = helper(root.right, p, q, ans);
        sum += leftSum + rightSum;
        if (sum == 2 && leftSum < 2 && rightSum < 2)
            ans.add(root);
        return sum;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> ans = new ArrayList<>();
        helper(root, p, q, ans);
        return ans.get(0);
    }
}
