package com.rainz;

/*
 * Given an array A of integers, return true if and only if it is a valid mountain array.
 * Recall that A is a mountain array if and only if:
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 */
public class ValidMountainArray {
    public static void test(String args[]) {
        int[] input1 = {2,1};
        System.out.println(validMountainArray(input1));
        int[] input2 = {3,5,5};
        System.out.println(validMountainArray(input2));
        int[] input3 = {0,3,2,1};
        System.out.println(validMountainArray(input3));
    }

    public static boolean validMountainArray(int[] A) {
        if (A.length < 3)
            return false;
        boolean increasing = true;
        int peakIdx = -1;
        for (int i = 1; i < A.length; ++i) {
            if (A[i] == A[i-1])
                return false;
            if (A[i] > A[i-1] && !increasing)
                return false;
            if (A[i] < A[i-1] && increasing) {
                increasing = false;
                peakIdx = i - 1;
            }
        }
        return peakIdx > 0;
    }
}
