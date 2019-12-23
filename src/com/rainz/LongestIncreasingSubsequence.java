package com.rainz;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static void test(String args[]) {
        int[] input = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(input));
        int[] input2 = {1,3,6,7,9,4,10,5,6};
        System.out.println(lengthOfLIS(input2));
        int[] input3 = {0};
        System.out.println(lengthOfLIS(input3));
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            int longest = 1;
            for (int j = i-1; j >= 0; --j) {
                if (nums[j] < nums[i]) {
                    int len = 1 + dp[j];
                    if (longest < len)
                        longest = len;
                }
            }
            dp[i] = longest;
            if (ans < longest)
                ans = longest;
        }
        return ans;
    }
}
