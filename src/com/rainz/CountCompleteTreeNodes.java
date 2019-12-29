package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Given a complete binary tree, count the number of nodes.
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 */

public class CountCompleteTreeNodes {
    public static void test(String args[]) {
        TreeNode input1 = Main.TreeNode.buildTreeLevelOrder("1,2,3,4,5,6,null");
        System.out.println(countNodes(input1));
        TreeNode input2 = Main.TreeNode.buildTreeLevelOrder("1");
        System.out.println(countNodes(input2));
    }

    private static int findLeftDepth(TreeNode node) {
        int levels = 0;
        TreeNode curr = node;
        while (curr != null) {
            curr = curr.left;
            ++levels;
        }
        return levels-1; // depth = levels-1
    }

    public static int countNodes(TreeNode root) {
        // Handle special cases
        if (root == null)
            return 0;
        int totalDepth = findLeftDepth(root);
        if (totalDepth == 0)
            return 1;

        // Try each subtree to find o   ut where last row ends
        int lastRowCount = 0;
        TreeNode curr = root;
        int halfCount = 1 << (totalDepth-1);
        int currDepth = totalDepth;
        while (curr != null) {
            if (halfCount == 1) {
                // This is the last row above the incomplete row
                if (curr.left != null) {
                    ++lastRowCount;
                    if (curr.right != null)
                        ++lastRowCount;
                }
                break;
            }
            --currDepth;
            if (findLeftDepth(curr.right) < currDepth)
                curr = curr.left;
            else {
                // Left tree is full
                lastRowCount += halfCount;
                curr = curr.right;
            }
            halfCount >>= 1;
        }
        return (1 << totalDepth) - 1 + lastRowCount;
    }
}
