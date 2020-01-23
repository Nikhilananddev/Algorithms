package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.*;

/*
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with same node values.
 */
public class FindDuplicateSubtrees {
    public static void test(String args[]) {
        System.out.println(findDuplicateSubtrees(TreeNode.buildTree("1,2,3,4,null,2,4,null,null,4")));

    }
    private static String serializeHelper(TreeNode root, Map<String, Integer> freqs, List<TreeNode> ans) {
        if (root == null) {
            return "# ";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        sb.append(" ");
        sb.append(serializeHelper(root.left, freqs, ans));
        sb.append(serializeHelper(root.right, freqs, ans));
        String s = sb.toString();
        int count = freqs.getOrDefault(s, 0) + 1;
        if (count == 2)
            ans.add(root); // only add once when count == 2
        freqs.put(s, count);
        return s;
    }
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        Map<String, Integer> freqs = new HashMap<>();
        serializeHelper(root, freqs, ans);
        return ans;
    }
}
