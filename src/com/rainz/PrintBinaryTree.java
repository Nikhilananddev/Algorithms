package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Print a binary tree in an m*n 2D string array following these rules:
 * The row number m should be equal to the height of the given binary tree.
 * The column number n should always be an odd number.
 * The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
 * Each unused space should contain an empty string "".
 * Print the subtrees following the same rules.
 */
public class PrintBinaryTree {
    public static void test(String args[]) {
        Main.printList2D(printTree(TreeNode.buildTree("1,2")));
        Main.printList2D(printTree(TreeNode.buildTree("1,2,3,null,4")));
        Main.printList2D(printTree(TreeNode.buildTree("1,2,5,3,null,null,null,4")));
        Main.printList2D(printTree(TreeNode.buildTree("1")));
    }

    private static int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    private static void helper(TreeNode root, int level, List<List<String>> ans, int left, int len) {
        if (root == null)
            return;
        int mid = left + len/2;
        ans.get(level).set(mid, Integer.toString(root.val));
        helper(root.left, level+1, ans, left, len/2);
        helper(root.right, level+1, ans, mid+1, len/2);
    }

    public static List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        int width = (int)(Math.round(Math.pow(2, height))) - 1;
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < height; ++i) {
            List<String> level = new ArrayList<>();
            ans.add(level);
            for (int j = 0; j < width; ++j)
                level.add("");
        }
        helper(root, 0, ans, 0, width);
        return ans;
    }

}
