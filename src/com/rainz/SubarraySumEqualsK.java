package com.rainz;


/*
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 */

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public static void test(String args[]) {
        int[] input = {1,1,1};
        System.out.println(subarraySum(input, 2));
    }

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumTable = new HashMap<>();
        int ans = 0;
        int sum = 0;
        // Must put 0 in as the sum before 1st element!!!
        sumTable.put(0, 1);
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            // Search backwards to avoid double counting and counting myself if k=0
            Integer freq = sumTable.get(sum-k);
            if (freq == null)
                freq = 0;
            ans += freq;
            // Now insert my sum into table
            freq = sumTable.get(sum);
            if (freq == null)
                freq = 0;
            sumTable.put(sum, freq+1);
        }
        return ans;
    }
}
