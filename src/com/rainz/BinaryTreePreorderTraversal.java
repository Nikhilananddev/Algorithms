package com.rainz;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.rainz.Main.TreeNode;

public class BinaryTreePreorderTraversal {
    public static void test(String args[]) {
        TreeNode input = TreeNode.buildTree("1,null,2,3");
        Main.printList(preorderTraversal(input));
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        TreeNode node = root;
        while (node != null || !stk.isEmpty()) {
            if (node == null) {
                node = stk.pop().right;
            } else {
                ans.add(node.val);
                stk.push(node);
                node = node.left;
            }
        }
        return ans;
    }
}
