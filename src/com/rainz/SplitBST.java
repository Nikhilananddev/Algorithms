package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a Binary Search Tree (BST) with root node root, and a target value V, split the tree into two subtrees where one subtree has nodes that are all smaller or equal to the target value, while the other subtree has all nodes that are greater than the target value.  It's not necessarily the case that the tree contains a node with value V.
 * Additionally, most of the structure of the original tree should remain.  Formally, for any child C with parent P in the original tree, if they are both in the same subtree after the split, then node C should still have the parent P.
 * You should output the root TreeNode of both subtrees after splitting, in any order.
 */
public class SplitBST {
    public static void test(String args[]) {
        TreeNode t1 = TreeNode.buildTree("4,2,6,1,3,5,7");
        TreeNode ans1[] = splitBST(t1, 2);
        System.out.println(ans1[0]);
        System.out.println(ans1[1]);
    }

    public static TreeNode[] splitBST(TreeNode root, int V) {
        if (root == null)
            return new TreeNode[]{null, null};
        else if (root.val <= V) {
            // The split will occur somewhere in the right subtree
            // So we make a recursive call into root.right
            TreeNode[] split = splitBST(root.right, V);
            // The result will include a subtree <=V and another >V
            // So we attach the first part to root.right since they are all <=V
            root.right = split[0];
            // And update left part to include the entire
            split[0] = root;
            return split;
        } else {
            // Similar to the first case
            TreeNode[] split = splitBST(root.left, V);
            root.left = split[1];
            split[1] = root;
            return split;
        }
    }

}
