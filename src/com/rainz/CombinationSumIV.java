package com.rainz;

import java.util.HashMap;

/*
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 */

public class CombinationSumIV {
    public static void test(String args[]) {
        int[] input = {1, 2, 3};
        System.out.println(combinationSum4(input, 4));
    }

    /* This solution uses memoization. Can easily convert this to 1-D DP */
    private static int helper(int[] nums, int target, HashMap<Integer, Integer> cache) {
        if (cache.containsKey(target))
            return cache.get(target);
        int result = 0;
        for (int i = 0; i < nums.length; ++i) {
            int n = nums[i];
            if (n > target)
                continue;
            if (n == target) {
                ++result;
                continue;
            }
            result += helper(nums, target - n, cache);
        }
        cache.put(target, result);
        return result;
    }

    public static int combinationSum4(int[] nums, int target) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        return helper(nums, target, cache);
    }
}
