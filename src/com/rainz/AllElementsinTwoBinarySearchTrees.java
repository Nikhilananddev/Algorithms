package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Given two binary search trees root1 and root2.
 * Return a list containing all the integers from both trees sorted in ascending order.
 */
public class AllElementsinTwoBinarySearchTrees {
    public static void test(String args[]) {
        TreeNode t11 = TreeNode.buildTree("2,1,4");
        TreeNode t12 = TreeNode.buildTree("1,0,3");
        System.out.println(getAllElements(t11, t12));
        TreeNode t21 = TreeNode.buildTree("0,-10,10");
        TreeNode t22 = TreeNode.buildTree("5,1,7,0,2");
        System.out.println(getAllElements(t21, t22));
        TreeNode t31 = TreeNode.buildTree("");
        TreeNode t32 = TreeNode.buildTree("5,1,7,0,2");
        System.out.println(getAllElements(t31, t32));
        TreeNode t41 = TreeNode.buildTree("0,-10,10");
        TreeNode t42 = TreeNode.buildTree("");
        System.out.println(getAllElements(t41, t42));
    }

    private static TreeNode leftMost(TreeNode root, Stack<TreeNode> stk) {
        TreeNode curr = root;
        while (curr != null) {
            stk.push(curr);
            curr = curr.left;
        }
        if (!stk.isEmpty())
            curr = stk.pop();
        return curr;
    }

    private static TreeNode nextNode(TreeNode node, Stack<TreeNode> stk) {
        if (node.right != null)
            return leftMost(node.right, stk);
        if (!stk.isEmpty())
            return stk.pop();
        return null;
    }

    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stk1 = new Stack<>();
        Stack<TreeNode> stk2 = new Stack<>();
        TreeNode curr1 = leftMost(root1, stk1);
        TreeNode curr2 = leftMost(root2, stk2);

        while (curr1 != null || curr2 != null) {
            if (curr1 == null || (curr2 != null && curr2.val < curr1.val)) {
                ans.add(curr2.val);
                curr2 = nextNode(curr2, stk2);
            } else {
                ans.add(curr1.val);
                curr1 = nextNode(curr1, stk1);
            }
        }
        return ans;
    }
}
