package com.rainz;

import java.util.TreeMap;
import java.util.Map;

/*
 * Given an array nums of integers, you can perform operations on the array.
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
 */

public class DeleteandEarn {
    public static void test(String args[]) {
        int[] input1 = {3, 4, 2};
        System.out.println(deleteAndEarn(input1));
        int[] input2 = {2, 2, 3, 3, 3, 4};
        System.out.println(deleteAndEarn(input2));
    }

    private static int helper(Integer[] vals, int start, Map<Integer, Integer> freqs) {
        if (start >= vals.length)
            return 0;
        int num = vals[start];
        int currPoints = num*freqs.get(num);
        // If next one is not current+1, always keep current
        if (start == vals.length-1 || vals[start+1] != num+1)
            return currPoints + helper(vals, start+1, freqs);
        // Pick larger result of keeping or discarding current num
        int keepCurrent = currPoints + helper(vals, start+2, freqs);
        int discardCurrent = helper(vals, start+1, freqs);
        return keepCurrent > discardCurrent ? keepCurrent : discardCurrent;
    }

    public static int deleteAndEarnRecursive(int[] nums) {
        Map<Integer, Integer> freqs = new TreeMap<>();
        for (int n: nums) {
            Integer freq = freqs.get(n);
            if (freq == null)
                freq = 0;
            freqs.put(n, freq+1);
        }
        Integer[] vals = freqs.keySet().toArray(new Integer[freqs.size()]);
        return helper(vals, 0, freqs);
    }

    public static int deleteAndEarn(int[] nums) {
        if (nums.length == 0)
            return 0;
        Map<Integer, Integer> freqs = new TreeMap<>();
        for (int n: nums) {
            Integer freq = freqs.get(n);
            if (freq == null)
                freq = 0;
            freqs.put(n, freq+1);
        }
        Integer[] vals = freqs.keySet().toArray(new Integer[freqs.size()]);
        int[] dp = new int[vals.length];
        for (int i = dp.length-1; i >= 0; --i) {
            int val = vals[i];
            if (i >= dp.length-1) {
                dp[i] = val * freqs.get(val);
                continue;
            }
            int val1 = vals[i+1];
            int dp2 = i + 2 < dp.length ? dp[i+2] : 0;
            if (val + 1 != val1)
                dp[i] = val*freqs.get(val) + dp[i+1]; // keep both
            else {
                int keepCurr = val*freqs.get(val) + dp2; // keep curr & skip next
                int discardCurr = dp[i+1]; // skip curr
                dp[i] = keepCurr > discardCurr ? keepCurr : discardCurr;
            }
        }
        return dp[0];
    }

}
