package com.rainz;

/*
 * Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 * Return the largest sum of the given array after partitioning.
 */

import java.util.Arrays;

public class PartitionArrayforMaximumSum {
    public static void test(String args[]) {
        int[] input = {1,15,7,9,2,5,10};
        System.out.println(maxSumAfterPartitioning(input, 3));
        int[] input2 = {1,4,1,5,7,3,6,1,9,9,3};
        System.out.println(maxSumAfterPartitioning(input2, 4));
    }

    public static int maxSumAfterPartitioning(int[] A, int K) {
        int[] dp = new int[A.length+1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; ++i) {
            int idx = i - 1; // index into array
            int num = A[idx];
            int maxSum = num + dp[i-1];
            int max = num;
            // Go back up to K-1 numbers to form partition and find maxSum
            for (int j = 1; j < K; ++j) {
                int prevIdx = idx - j;
                if (prevIdx < 0)
                    break;
                int prev = A[prevIdx];
                if (prev > max)
                    max = prev;
                int sum = max * (j+1) + dp[i-j-1];
                if (sum > maxSum)
                    maxSum = sum;
            }
            dp[i] = maxSum;
        }
        return dp[A.length];
    }
}
