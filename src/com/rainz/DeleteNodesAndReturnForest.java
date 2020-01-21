package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.*;

/*
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 */
public class DeleteNodesAndReturnForest {
    public static void test(String args[]) {
        int[] toDel1 = {3,5};
        System.out.println(delNodes(TreeNode.buildTree("1,2,3,4,5,6,7"), toDel1));
    }

    // Return tree if this node is to be kept, false if it is to be deleted
    private static boolean helper(TreeNode root, Set<Integer> toDel, boolean keepParent, List<TreeNode> ans) {
        if (root == null)
            return true;
        boolean keep = !toDel.contains(root.val);
        if (keep && !keepParent)
            ans.add(root);
        boolean leftKeep = helper(root.left, toDel, keep, ans);
        if (!leftKeep)
            root.left = null;
        boolean rightKeep = helper(root.right, toDel, keep, ans);
        if (!rightKeep)
            root.right = null;
        return keep;
    }

    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDel = new HashSet<>();
        for (int d: to_delete)
            toDel.add(d);
        List<TreeNode> ans = new ArrayList<>();
        helper(root, toDel, false, ans);
        return ans;
    }
}
