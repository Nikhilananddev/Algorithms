package com.rainz;

import java.util.HashMap;
import java.util.Map;

public class LengthofLongestFibonacciSubsequence {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,4,5,6,7,8};
        System.out.println(lenLongestFibSubseq(input1));
        int[] input2 = {1,3,7,11,12,14,18};
        System.out.println(lenLongestFibSubseq(input2));
        int[] input3 = {2,4,7,8,9,10,14,15,18,23,32,50};
        System.out.println(lenLongestFibSubseq(input3));
        int[] input4 = {1,3,5};
        System.out.println(lenLongestFibSubseq(input4));
        int[] input5 = {1,2};
        System.out.println(lenLongestFibSubseq(input5));
    }

    public static int lenLongestFibSubseq(int[] A) {
        Map<Integer, Integer> lookup = new HashMap<>();
        for (int i = 0; i < A.length; ++i)
            lookup.put(A[i], i);

        int[][] dp = new int[A.length][A.length];
        int result = 0;
        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < i; ++j) {
                // look for sequence a, b, A[i], where A[i]=a+b
                int a = A[j];
                int b = A[i] - a;
                if (b <= a)
                    continue;
                Integer bIdx = lookup.get(b);
                if (bIdx == null)
                    continue; // can't find b where a+b=A[i]
                // so a+b=A[i], fibonacci sequence: a, b, A[i]
                int newLen = dp[j][bIdx] > 0 ? dp[j][bIdx] + 1 : 3;
                if (newLen > dp[bIdx][i]) {
                    dp[bIdx][i] = newLen;
                    if (newLen > result)
                        result = newLen;
                }
            }
        }
        return result;
    }
}
