package com.rainz;

import java.util.*;

/*
 * Given two arrays, write a function to compute their intersection.
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 */
public class IntersectionofTwoArraysII {
    public static void test(String args[]) {
        int[] nums11 = {1,2,2,1}, nums12 = {2,2};
        Main.printArray(intersect(nums11, nums12));
        int[] nums21 = {4,9,5}, nums22 = {9,4,9,8,4};
        Main.printArray(intersect(nums21, nums22));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> freqs = new HashMap<>();
        for (int n: nums1)
            freqs.put(n, freqs.getOrDefault(n, 0)+1);
        List<Integer> common = new ArrayList<>();
        for (int n: nums2) {
            int count = freqs.getOrDefault(n, 0);
            if (count > 0) {
                freqs.put(n, count - 1);
                common.add(n);
            }
        }
        int[] ans = new int[common.size()];
        for (int i = 0; i < ans.length; ++i)
            ans[i] = common.get(i);
        return ans;
    }
}
