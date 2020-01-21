package com.rainz;

/*
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 */
public class SplitArrayLargestSum {
    public static void test(String args[]) {
        int[] input1 = {7,2,5,10,8};
        System.out.println(splitArray(input1, 2));
        int[] input2 = {1,2147483646};
        System.out.println(splitArray(input2, 1));
        int[] input3 = {1,2147483647};
        System.out.println(splitArray(input3, 2));
    }

    /*
     * Binary search the min of max sum and try splitting with it.
     */

    private static boolean canSplit(int[] nums, int m, long limit) {
        long sum = 0;
        int parts = 1;
        for (int i = 0; i < nums.length; ++i) {
            if (sum + nums[i] > limit) {
                ++parts;
                sum = nums[i];
            } else
                sum += nums[i];
        }
        return parts <= m;
    }

    public static int splitArray(int[] nums, int m) {
        int maxVal = 0;
        for (int n: nums) {
            if (n > maxVal)
                maxVal = n;
        }
        long lo = maxVal, hi = Integer.MAX_VALUE;
        int ans = 0;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (canSplit(nums, m, mid)) {
                ans = (int)mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }
}
