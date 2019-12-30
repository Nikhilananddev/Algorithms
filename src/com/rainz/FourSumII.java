package com.rainz;

import java.util.HashMap;
import java.util.Map;

/*
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 */
public class FourSumII {
    public static void test(String args[]) {
        int[] A = { 1, 2};
        int[] B = {-2,-1};
        int[] C = {-1, 2};
        int[] D = { 0, 2};
        System.out.println(fourSumCount(A, B, C, D));
    }
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> abSums = new HashMap<>();
        Map<Integer, Integer> cdSums = new HashMap<>();
        for (int a: A) {
            for (int b: B) {
                int s = a + b;
                abSums.put(s, abSums.getOrDefault(s, 0)+1);
            }
        }
        for (int c: C) {
            for (int d: D) {
                int s = c + d;
                cdSums.put(s, cdSums.getOrDefault(s, 0)+1);
            }
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry: abSums.entrySet()) {
            int cdCount = cdSums.getOrDefault(-entry.getKey(), 0);
            ans += entry.getValue() * cdCount;
        }
        return ans;
    }
}
