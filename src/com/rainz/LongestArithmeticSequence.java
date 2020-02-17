package com.rainz;

/*
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 */

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSequence {
    public static void test(String args[]) {
        int[] input1 = {3,6,9,12};
        System.out.println(longestArithSeqLength(input1));
        int[] input2 = {9,4,7,2,10};
        System.out.println(longestArithSeqLength(input2));
        int[] input3 = {20,1,15,3,10,5,8};
        System.out.println(longestArithSeqLength(input3));
    }
    /*
     * An arithmetic sequence is defined by a "delta" and a start/end (or any number in it)
     * Consider each i as end of a sequence so far.
     * Any number j before i may form a sequence with i with delta being A[i]-A[j].
     * So for each i, store (delta, seqLen) pairs of all possible sequences ending here at i in a hashmap.
     */
    public static int longestArithSeqLength(int[] A) {
        int N = A.length;
        int ans = Integer.MIN_VALUE;
        Map<Integer, Integer>[] dp = new Map[N]; // k: delta, v: seqLen
        for (int i = 0; i < N; ++i) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; ++j) {
                int delta = A[i] - A[j];
                int seqLen = dp[j].getOrDefault(delta, 0) + 1;
                dp[i].put(delta, seqLen);
                if (seqLen > ans)
                    ans = seqLen;
            }
        }
        return ans+1;
    }

}
