package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
    public static void test(String args[]) {
        TreeNode input = TreeNode.buildTree("1,null,2,3");
        Main.printList(inorderTraversal(input));
    }
    /*
     * Non-recursive in-order traversal:
     * For each node: push onto stack, then keep going to left until null.
     * When we see null, time to pop node from stack, print it, then go to right
     * Note: when we see null,
     * - If this is left child, popping the stack will return root;
     * - If this is right child, popping the stack will return parent of root;
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        TreeNode node = root;
        while (node != null || !stk.isEmpty()) {
            if (node == null) {
                node = stk.pop();
                ans.add(node.val);
                node = node.right;
            } else {
                stk.push(node);
                node = node.left;
            }
        }
        return ans;
    }

    /*
     * Morris Traversal
     * - For root, check the right most node (RMN) of root.left (1st time) and if null (as expect), link it to root.
     * - Traverse root.left. You'll naturally come back to root via the newly added link, after root.left traversal is complete.
     * - You'll naturally check RMN of root.left again (2nd time) and this time, break its link to root.
     *   - You can determine this is the 2nd visit by comparing RMN.right and root.
     * - Then visit root and root.right as you normally would.
     */
    public static List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        TreeNode cur, pre;
        cur = root;
        while (cur != null) {
            if (cur.left == null) {
                // If no left, just visit cur and go to right as you normally would.
                res.add(cur.val);
                cur = cur.right;
            } else {
                // Go to right most node of left
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) {
                    // First time visit, link right most node to cur
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    // Second time visit, break the link to cur
                    pre.right = null;
                    // Then visit cur and go to right as you normally would.
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
