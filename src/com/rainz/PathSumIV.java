package com.rainz;

/*
 * f the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.
 * For each integer in this list:
 * The hundreds digit represents the depth D of this node, 1 <= D <= 4.
 * The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
 * The units digit represents the value V of this node, 0 <= V <= 9.
 * Given a list of ascending three-digits integers representing a binary tree with the depth smaller than 5, you need to return the sum of all paths from the root towards the leaves.
 */

import java.util.HashMap;
import java.util.Map;

public class PathSumIV {
    public static void test(String args[]) {
        int[] input1 = {113, 215, 221};
        System.out.println(pathSum(input1));
        int[] input2 = {113, 221};
        System.out.println(pathSum(input2));
    }

    public static int pathSum(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> nodes = new HashMap<>();
        for (int i = nums.length-1; i >= 0; --i) {
            int n = nums[i];
            int rc = n / 10;
            int r = rc / 10;
            int c = rc % 10;
            int v = n % 10;
            int count = nodes.getOrDefault(rc, 1); // if not found, its a leaf
            ans += v * count;
            // Now contribute to parent
            if (r > 1) {
                int parent = (r-1)*10 + (c+1)/2;
                nodes.put(parent, nodes.getOrDefault(parent, 0) + count);
            }
        }
        return ans;
    }
}
