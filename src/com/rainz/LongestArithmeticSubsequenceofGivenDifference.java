package com.rainz;

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubsequenceofGivenDifference {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,4};
        System.out.println(longestSubsequence(input1, 1));
        int[] input2 = {1,3,5,7};
        System.out.println(longestSubsequence(input2, 1));
        int[] input3 = {1,5,7,8,5,3,4,2,1};
        System.out.println(longestSubsequence(input3, -2));
    }

    public static int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> seqMap = new HashMap<>();
        int result = 0;
        for (int a: arr) {
            int prev = a - difference;
            Integer seqLen = seqMap.get(prev);
            if (seqLen != null) {
                // Found a sequence
                ++seqLen;
                seqMap.put(a, seqLen);
                seqMap.remove(prev);
            } else {
                if (seqMap.get(a) == null) {
                    seqLen = 1;
                    seqMap.put(a, seqLen);
                }
            }
            if (seqLen != null && seqLen > result)
                result = seqLen;
        }
        return result;
    }
}
