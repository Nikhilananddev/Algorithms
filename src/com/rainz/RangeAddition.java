package com.rainz;

/*
 * Assume you have an array of length n initialized with all 0's and are given k update operations.
 * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
 * Return the modified array after all k operations were executed.
 */

public class RangeAddition {
    public static void test(String args[]) {
        int[][] input1 = {{1,3,2},{2,4,3},{0,2,-2}};
        Main.printArray(getModifiedArray(5, input1));
    }

    public static int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        for (int[] u: updates) {
            ans[u[0]] += u[2];
            if (u[1]+1 < length)
                ans[u[1]+1] -= u[2];
        }
        for (int i = 1; i < length; ++i)
            ans[i] += ans[i-1];
        return ans;
    }
    
}
