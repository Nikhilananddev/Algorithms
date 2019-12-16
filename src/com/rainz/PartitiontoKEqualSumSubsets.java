package com.rainz;

public class PartitiontoKEqualSumSubsets {
    public static void test(String args[]) {
        int[] input = {4, 3, 2, 3, 5, 2, 1};
        System.out.println(canPartitionKSubsets(input, 4));
    }

    private static boolean helper(int[] nums, int start, int avg, int[] buckets) {
        if (start >= nums.length) {
            // Check each bucket
            for (int sum: buckets)
                if (sum != avg)
                    return false;
            return true;
        }
        // Try putting current number in each of the buckets
        for (int bkt = 0; bkt < buckets.length;++bkt) {
            buckets[bkt] += nums[start];
            if (buckets[bkt] <= avg && helper(nums, start+1, avg, buckets))
                return true;
            buckets[bkt] -= nums[start];
        }
        return false;
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums.length < k || k < 1)
            return false;
        int sum = 0;
        for (int n: nums)
            sum += n;
        if (sum % k != 0)
            return false;
        int avg = sum / k;
        int[] buckets = new int[k];
        return helper(nums, 0, avg, buckets);
    }
}
