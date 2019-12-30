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
     * For each node push onto stack, then go to left
     * If null, time to pop node from stack, print it, then go to right
     * Note: when right is null and you pop, you'll get the node above its parent, as expected.
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
}
