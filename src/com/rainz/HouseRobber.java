package com.rainz;

/*
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

public class HouseRobber {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,1};
        System.out.println(rob(input1));
        int[] input2 = {2,7,9,3,1};
        System.out.println(rob(input2));
    }

    public static int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int prevSum = 0;
            if (i >= 2)
                prevSum = dp[i-2];
            int thisSum = prevSum + nums[i];
            dp[i] = thisSum > dp[i-1] ? thisSum : dp[i-1];
        }
        return dp[nums.length-1];
    }
}
