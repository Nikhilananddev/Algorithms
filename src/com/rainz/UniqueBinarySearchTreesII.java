package com.rainz;

import java.util.ArrayList;
import java.util.List;
import com.rainz.Main.TreeNode;

/*
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 */

public class UniqueBinarySearchTreesII {
    public static void test(String args[]) {
        Main.printList(generateTrees(3));
    }

    private static List<TreeNode> helper(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start >= end) {
            result.add(null);
            return result;
        }
        for (int i = start; i < end; ++i) {
            List<TreeNode> lefts  = helper(start, i);
            List<TreeNode> rights = helper(i+1, end);
            for (TreeNode l: lefts) {
                for (TreeNode r: rights) {
                    TreeNode curr = new TreeNode(i);
                    curr.left = l;
                    curr.right = r;
                    result.add(curr);
                }
            }
        }
        return result;
    }

    public static List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList<>(); // Otherwise we get [[]] instead of []
        return helper(1, n+1);
    }
}
