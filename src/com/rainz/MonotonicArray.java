package com.rainz;

/*
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
 * Return true if and only if the given array A is monotonic.
 */
public class MonotonicArray {
    public static void test(String args[]) {
        int[] input1 = {1,2,2,3};
        System.out.println(isMonotonic(input1));
        int[] input2 = {6,5,4,4};
        System.out.println(isMonotonic(input2));
        int[] input3 = {1,3,2};
        System.out.println(isMonotonic(input3));
        int[] input4 = {1,2,4,5};
        System.out.println(isMonotonic(input4));
        int[] input5 = {1,1,1};
        System.out.println(isMonotonic(input5));
    }
    public static boolean isMonotonic(int[] A) {
        if (A.length == 1)
            return true;
        int increase = 0;
        for (int i = 1; i < A.length; ++i) {
            if (A[i] > A[i-1]) {
                if (increase < 0)
                    return false;
                increase = 1;
            } else if (A[i] < A[i-1]) {
                if (increase > 0)
                    return false;
                increase = -1;
            }
        }
        return true;
    }
}
