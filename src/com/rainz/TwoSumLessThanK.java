package com.rainz;

import java.util.Arrays;

/*
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.
 * 1 <= A.length <= 100
 * 1 <= A[i] <= 1000
 * 1 <= K <= 2000
 */
public class TwoSumLessThanK {
    public static void test(String args[]) {
        int[] input1 = {34,23,1,24,75,33,54,8};
        System.out.println(twoSumLessThanK(input1, 60));
        int[] input2 = {10,20,30};
        System.out.println(twoSumLessThanK(input2, 15));
        int[] input3 = {254,914,110,900,147,441,209,122,571,942,136,350,160,127,178,839,201,386,462,45,735,467,153,415,875,282,204,534,639,994,284,320,865,468,1,838,275,370,295,574,309,268,415,385,786,62,359,78,854,944};
        System.out.println(twoSumLessThanK(input3, 200));
    }

    public static int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);
        int ans = -1;
        for (int i = 0; i < A.length; ++i) {
            int target = K - A[i];
            int lo = i + 1, hi = A.length - 1;
            int max = -1;
            while (lo <= hi) {
                int mid = (lo + hi)/2;
                if (A[mid] < target) {
                    max = A[mid];
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            if (max >= 0 && max + A[i] > ans)
                ans = max + A[i];
        }
        return ans;
    }
}
