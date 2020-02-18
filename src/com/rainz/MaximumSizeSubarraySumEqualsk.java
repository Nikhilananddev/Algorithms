package com.rainz;

/*
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 */

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarraySumEqualsk {
    public static void test(String args[]) {
        int[] input1 = {1, -1, 5, -2, 3};
        System.out.println(maxSubArrayLen(input1, 3));
        int[] input2 = {-2, -1, 2, 1};
        System.out.println(maxSubArrayLen(input2, 1));
    }
    public static int maxSubArrayLen(int[] nums, int k) {
        int ans = 0;
        int sum = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1);
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            Integer prev = sumMap.get(sum-k);
            if (prev != null) {
                int win = i - prev;
                if (win > ans)
                    ans = win;
            }
            sumMap.putIfAbsent(sum, i);
        }
        return ans;
    }
}
