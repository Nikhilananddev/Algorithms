package com.rainz;

import java.util.HashMap;
import java.util.Map;

/*
 *We define a harmounious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
 */
public class LongestHarmoniousSubsequence {
    public static void test(String args[]) {
        int[] input1 = {1,3,2,2,5,2,3,7};
        System.out.println(findLHS(input1));
    }

    /*
     * Length of a harmounious subsequence is just a count of numbers within +/-1. So use a hashmap to count frequencies.
     */
    public static int findLHS(int[] nums) {
        Map<Integer, Integer> freqs = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            int count = freqs.getOrDefault(num, 0) + 1;
            freqs.put(num, count);
            int count1 = freqs.getOrDefault(num+1, 0);
            if (count1 > 0) {
                int len = count1 + count;
                if (len > ans)
                    ans = len;
            }
            int count2 = freqs.getOrDefault(num-1, 0);
            if (count2 > 0) {
                int len = count2 + count;
                if (len > ans)
                    ans = len;
            }
        }
        return ans;
    }
}
