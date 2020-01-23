package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
 */
public class MaximumWidthofBinaryTree {
    public static void test(String args[]) {
        System.out.println(widthOfBinaryTree(Main.TreeNode.buildTree("1,3,2,5,3,null,9")));
        System.out.println(widthOfBinaryTree(Main.TreeNode.buildTree("1,3,null,5,3")));
        System.out.println(widthOfBinaryTree(Main.TreeNode.buildTree("1,3,2,5")));
        System.out.println(widthOfBinaryTree(Main.TreeNode.buildTree("1,3,2,5,null,null,9,6,null,null,7")));
    }
    private static class Rec {
        public TreeNode node;
        public int index;
        public Rec(TreeNode n, int idx) { node = n; index = idx; }
    }
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int ans = 0;
        List<Rec> curr = new ArrayList<>();
        curr.add(new Rec(root, 1));
        while (!curr.isEmpty()) {
            List<Rec> next = new ArrayList<>();
            int minIdx = Integer.MAX_VALUE, maxIdx = Integer.MIN_VALUE;
            for (Rec r: curr) {
                if (r.index < minIdx)
                    minIdx = r.index;
                if (r.index > maxIdx)
                    maxIdx = r.index;
                if (r.node.left != null)
                    next.add(new Rec(r.node.left, (r.index-1)*2+1));
                if (r.node.right != null)
                    next.add(new Rec(r.node.right, (r.index-1)*2+2));
            }
            if (maxIdx - minIdx + 1 > ans)
                ans = maxIdx - minIdx + 1;
            curr = next;
        }
        return ans;
    }
}
