package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
 */

public class MostFrequentSubtreeSum {
    public static void test(String args[]) {
        Main.printArray(findFrequentTreeSum(TreeNode.buildTree("5,2,-3")));
        Main.printArray(findFrequentTreeSum(TreeNode.buildTree("5,2,-5")));
    }

    private static int computeSum(TreeNode root, Map<Integer, List<Integer>> sumTbl) {
        if (root == null)
            return 0;
        int sum = root.val + computeSum(root.left, sumTbl) + computeSum(root.right, sumTbl);
        List<Integer> vals = sumTbl.get(sum);
        if (vals == null) {
            vals = new ArrayList<>();
            sumTbl.put(sum, vals);
        }
        vals.add(root.val);
        return sum;
    }

    public static int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, List<Integer>> sumTbl = new HashMap<>();
        computeSum(root, sumTbl);
        int maxCount = Integer.MIN_VALUE;
        List<Integer> maxList = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry: sumTbl.entrySet()) {
            int count = entry.getValue().size();
            if (count > maxCount) {
                maxCount = count;
                maxList.clear();
                maxList.add(entry.getKey());
            } else if (count == maxCount)
                maxList.add(entry.getKey());
        }
        int[] ans = new int[maxList.size()];
        for (int i = 0; i < ans.length; ++i)
            ans[i] = maxList.get(i);
        return ans;
    }

}
