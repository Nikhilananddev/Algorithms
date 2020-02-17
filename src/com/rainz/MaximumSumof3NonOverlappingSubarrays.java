package com.rainz;

/*
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 * Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.
 */
public class MaximumSumof3NonOverlappingSubarrays {
    public static void test(String args[]) {
        int[] input1 = {1,2,1,2,6,7,5,1};
        Main.printArray(maxSumOfThreeSubarrays(input1, 2));
        int[] input2 = {1,2,3,4,5,6};
        Main.printArray(maxSumOfThreeSubarrays(input2, 2));
        int[] input3 = {1,2,1,2,1,2,1,2,1};
        Main.printArray(maxSumOfThreeSubarrays(input3, 2));
        int[] input4 = {7,13,20,19,19,2,10,1,1,19};
        Main.printArray(maxSumOfThreeSubarrays(input4, 3));
    }

    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int N = nums.length;
        int[] preSum = new int[N+1];
        for (int i = 1; i <= N; ++i)
            preSum[i] = nums[i-1] + preSum[i-1];

        int[] leftMax = new int[N];
        int maxSum = Integer.MIN_VALUE;
        for (int end = k-1; end < N - 2*k; ++end) {
            int start = end + 1 - k;
            int kSum = preSum[end+1] - preSum[start];
            if (kSum > maxSum) {
                maxSum = kSum;
                leftMax[start] = start;
            } else
                leftMax[start] = leftMax[start-1];
        }
        maxSum = Integer.MIN_VALUE;
        int[] rightMax = new int[N];
        for (int end = N-1; end >= 3*k-1; --end) {
            int start = end + 1 - k;
            int kSum = preSum[end+1] - preSum[start];
            if (kSum >= maxSum) {
                maxSum = kSum;
                rightMax[start] = start;
            } else
                rightMax[start] = rightMax[start+1];
        }

        int[] ans = {-1, -1, -1};
        maxSum = Integer.MIN_VALUE;
        for (int i = k; i + 2*k - 1 < N; ++i) {
            int lIdx = leftMax[i-k];
            int lSum = preSum[lIdx+k] - preSum[lIdx];
            int rIdx = rightMax[i+k];
            int rSum = preSum[rIdx+k] - preSum[rIdx];
            int sum = preSum[i+k] - preSum[i] + lSum + rSum;
            if (sum > maxSum) {
                maxSum = sum;
                ans[0] = lIdx;
                ans[1] = i;
                ans[2] = rIdx;
            }
        }
        return ans;
    }
}
