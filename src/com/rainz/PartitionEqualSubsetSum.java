package com.rainz;

/*
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 */

public class PartitionEqualSubsetSum {
    public static void test(String args[]) {
        int[] input1 = {1, 5, 11, 5};
        System.out.println(canPartition(input1));
        int[] input2 = {1, 2, 3, 5};
        System.out.println(canPartition(input2));
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n: nums)
            sum += n;
        if (sum % 2 == 1)
            return false;
        int target = sum / 2;
        boolean[][] dp = new boolean[target+1][nums.length+1];
        for (int i = 0; i <= nums.length; ++i)
            dp[0][i] = true;
        for (int t = 1; t <= target; ++t)
            dp[t][0] = false;
        for (int t = 1; t <= target; ++t) {
            for (int i = 1; i <= nums.length; ++i) {
                int n = nums[i-1];
                // Case 1: use current number
                int diff = t - n;
                boolean useResult = diff >= 0 ? dp[diff][i-1] : false;
                // Case 2: not use current number
                boolean notUseResult = dp[t][i-1];
                dp[t][i] = useResult || notUseResult;
            }
        }
        return dp[target][nums.length];
    }

}
