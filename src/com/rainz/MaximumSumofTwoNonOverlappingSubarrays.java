package com.rainz;

import java.util.Arrays;

/*
 * Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)
 */
public class MaximumSumofTwoNonOverlappingSubarrays {
    public static void test(String args[]) {
        int[] input1 = {0,6,5,2,2,5,1,9,4};
        System.out.println(maxSumTwoNoOverlap(input1, 1, 2));
        int[] input2 = {3,8,1,3,2,1,8,9,0};
        System.out.println(maxSumTwoNoOverlap(input2, 3, 2));
        int[] input3 = {2,1,5,6,0,9,5,0,3,8};
        System.out.println(maxSumTwoNoOverlap(input3, 4, 3));
    }

    public static int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int N = A.length;
        int ans = Integer.MIN_VALUE;
        int[] sums = new int[N+1]; // cumulative sum
        sums[0] = 0;
        for (int i = 0; i < N; ++i)
            sums[i+1] = sums[i] + A[i];
        int[] sumsL = new int[N]; // subarray sums with length L and start at i
        for (int i = 0; i + L <= N; ++i)
            sumsL[i] = sums[i+L] - sums[i];
        int[] sumsM = new int[N]; // subarray sums with length M and start at i
        for (int i = 0; i + M <= N; ++i)
            sumsM[i] = sums[i+M] - sums[i];

        // First handle the case where L is on the left
        // Compute max M from right
        int[] maxFromRight = new int[N];
        int maxSum = Integer.MIN_VALUE;
        for (int i = N - 1; i >= 0; --i) {
            if (maxSum < sumsM[i])
                maxSum = sumsM[i];
            maxFromRight[i] = maxSum;
        }
        // Compute L and max M on its right
        for (int i = 0; i + L + M <= N; ++i) {
            int sum = sumsL[i] + maxFromRight[i+L];
            if (sum > ans)
                ans = sum;
        }

        // Now handle the case where M is on the left
        // Compute max L from right
        Arrays.fill(maxFromRight, 0);
        maxSum = Integer.MIN_VALUE;
        for (int i = N - 1; i >= 0; --i) {
            if (maxSum < sumsL[i])
                maxSum = sumsL[i];
            maxFromRight[i] = maxSum;
        }
        // Compute M and max L on its right
        for (int i = 0; i + L + M <= N; ++i) {
            int sum = sumsM[i] + maxFromRight[i+M];
            if (sum > ans)
                ans = sum;
        }
        return ans;
    }
}
