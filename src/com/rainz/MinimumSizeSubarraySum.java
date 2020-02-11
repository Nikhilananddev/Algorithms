package com.rainz;

/*
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 */
public class MinimumSizeSubarraySum {
    public static void test(String args[]) {
        int[] input1 = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, input1));
    }

    public static int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0)
            return 0;

        int result = 0;
        int startIdx = 0;
        int endIdx = 1; // exclusive
        int windowSum = nums[0];
        while (startIdx < endIdx) {
            if (windowSum < s) {
                if (endIdx < nums.length)
                    windowSum += nums[endIdx++];
                else
                    break;
            }
            if (windowSum >= s) {
                int len = endIdx - startIdx;
                if (result > len || result == 0)
                    result = len;
                windowSum -= nums[startIdx++];
            }
        }
        return result;
    }
}
