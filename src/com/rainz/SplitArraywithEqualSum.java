package com.rainz;

/*
 * Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:
 * 0 < i, i + 1 < j, j + 1 < k < n - 1
 * Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
 * where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R
 */

/*
 * Basically divide an array into 4 equal sums, but the 3 dividing points are not included in the sums
 */

import java.util.HashSet;
import java.util.Set;

public class SplitArraywithEqualSum {
    public static void test(String args[]) {
        int[] input1 = {1,2,1,2,1,2,1};
        System.out.println(splitArray(input1));
        int[] input2 = {1,2,1,3,0,0,2,2,1,3,3};
        System.out.println(splitArray(input2));
        int[] input3 = {1,2,-1,1,2,5,2,5,2};
        System.out.println(splitArray(input3));
    }

    private static boolean dfs(int[] nums, int start, int level, int prevSum, int[] sums) {
        if (level == 3) {
            // Check sum of last section
            return prevSum == sums[nums.length] - sums[start];
        }
        for (int split = start+1; split < nums.length; ++split) {
            int sum = sums[split] - sums[start];
            if (level > 0 && sum != prevSum)
                continue;
            if (dfs(nums, split+1, level+1, sum, sums))
                return true;
        }
        return false;
    }

    public static boolean splitArrayRecursive(int[] nums) {
        int N = nums.length;
        int[] prefixSums = new int[N+1];
        for (int i = 0; i < N; ++i)
            prefixSums[i+1] = prefixSums[i] + nums[i];
        return dfs(nums, 0, 0, 0, prefixSums);
    }

    /*
     * Loop by mid point. Within each loop:
     * - Find all possible left splits save sums in a set
     * - Find all possible right splits and see if sum is in the set.
     */
    public static boolean splitArray(int[] nums) {
        int N = nums.length;
        int[] prefixSums = new int[N+1];
        for (int i = 0; i < N; ++i)
            prefixSums[i+1] = prefixSums[i] + nums[i];
        for (int mid = 3; mid < N - 3; ++mid) {
            Set<Integer> candidateSums = new HashSet<>();
            for (int midL = 1; midL < mid - 1; ++midL) {
                int qtrSum = prefixSums[mid] - prefixSums[midL + 1];
                if (qtrSum != prefixSums[midL] - prefixSums[0])
                    continue;
                candidateSums.add(qtrSum);
            }
            for (int midR = mid + 2; midR < N - 1; ++midR) {
                int qtrSum = prefixSums[N] - prefixSums[midR + 1];
                if (qtrSum == prefixSums[midR] - prefixSums[mid + 1] && candidateSums.contains(qtrSum))
                    return true;
            }
        }
        return false;
    }
}
