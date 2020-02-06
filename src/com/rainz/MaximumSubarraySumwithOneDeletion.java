package com.rainz;

/*
 * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.
 * Note that the subarray needs to be non-empty after deleting one element.
 */
public class MaximumSubarraySumwithOneDeletion {
    public static void test(String args[]) {
        int[] input1 = {1,-2,0,3};
        System.out.println(maximumSum(input1));
        int[] input2 = {1,-2,-2,3};
        System.out.println(maximumSum(input2));
        int[] input3 = {-1,-1,-1,-1};
        System.out.println(maximumSum(input3));
    }

    public static int maximumSum(int[] arr) {
        if (arr.length == 0)
            return 0;
        int ans = arr[0];
        int delMax = 0; // "ending here" for exactly one delete
        int noDelMax = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            int n = arr[i];
            delMax = Math.max(delMax+n, noDelMax); // (deleted previously, delete current)
            noDelMax = Math.max(noDelMax+n, n);
            ans = Math.max(ans, Math.max(delMax, noDelMax));
        }
        return ans;
    }
}
