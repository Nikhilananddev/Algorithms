package com.rainz;

/*
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

public class HouseRobberII {
    public static void test(String args[]) {
        int[] input1 = {2,3,2};
        System.out.println(rob(input1));
        int[] input2 = {1,2,3,1};
        System.out.println(rob(input2));
        int[] input3 = {1,1,1,2};
        System.out.println(rob(input3));
        int[] input4 = {1,1,3,6,7,10,7,1,8,5,9,1,4,4,3};
        System.out.println(rob(input4));
    }

    public static int helper(int[] nums, int start, int end) {
        int len = end - start;
        int[] dp = new int[len];
        dp[0] = nums[start];
        for (int i = 1; i < len; ++i) {
            int idx = i + start;
            int prevSum = 0;
            if (i >= 2)
                prevSum = dp[i-2];
            int thisSum = prevSum + nums[idx];
            dp[i] = thisSum > dp[i-1] ? thisSum : dp[i-1];
        }
        return dp[len-1];
    }

    public static int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0]; // note: the 2 calls below doesn't cover this case. Must be handled!!!
        int includeFirst = helper(nums, 0, nums.length-1);
        int excludeFirst = helper(nums, 1, nums.length);
        return includeFirst > excludeFirst ? includeFirst : excludeFirst;
    }
}
