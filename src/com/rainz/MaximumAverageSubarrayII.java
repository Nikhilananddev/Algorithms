package com.rainz;

/*
 * Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. And you need to output the maximum average value.
 */
public class MaximumAverageSubarrayII {
    public static void test(String args[]) {
        int[] input1 = {1,12,-5,-6,50,3};
        System.out.println(findMaxAverage(input1, 4));
    }

    public static double findMaxAverageFailed(int[] nums, int k) {
        int N = nums.length;
        int[] prefixSum = new int[N+1];
        for (int i = 1; i < prefixSum.length; ++i)
            prefixSum[i] = nums[i-1] + prefixSum[i-1];
        double prevAvg = (double)(prefixSum[k] - prefixSum[0])/k; // avg of first k nums
        double ans = prevAvg;
        int start = 0;
        int end = k; // exclusive
        // Compute max avg ending here for each num
        while (end < nums.length) {
            ++end;
            double avg1 = (double)(prefixSum[end] - prefixSum[start])/(end - start); // add curr to avg
            double avg2 = (double)(prefixSum[end] - prefixSum[end-k])/k; // Used the last k
            prevAvg = Math.max(avg1, avg2);
            if (avg1 <= avg2)
                start = end - k;
            ans = Math.max(ans, prevAvg);
        }
        return ans;
    }

    private static boolean checkAvg(double avg, int[] nums, int k) {
        double sum = 0, prev = 0, minSum = 0;
        // Compute sum of difference from avg
        // If difference >= 0, we find a range with average >= avg
        for (int i = 0; i < k; i++)
            sum += nums[i] - avg;
        if (sum >= 0)
            return true; // averge of first k elements >= avg
        for (int i = k; i < nums.length; i++) {
            // Use prefix sum
            sum += nums[i] - avg; // current prefix sum
            prev += nums[i - k] - avg; // prefix sum k elements ago
            minSum = Math.min(prev, minSum); // min prefix sum k elements ago
            if (sum >= minSum)
                return true; // average of the past n (>=k) elements >= avg
        }
        return false;

    }

    // Binary search
    public static double findMaxAverage(int[] nums, int k) {
        double lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        for (int n: nums) {
            if (n < lo)
                lo = n;
            if (n > hi)
                hi = n;
        }
        double error = Integer.MAX_VALUE;
        double prevMid = hi;
        while (error > 0.00001) {
            double mid = (lo + hi) / 2;
            if (checkAvg(mid, nums, k))
                lo = mid;
            else
                hi = mid;
            error = Math.abs(prevMid - mid);
            prevMid = mid;
        }
        return lo;
    }
}
