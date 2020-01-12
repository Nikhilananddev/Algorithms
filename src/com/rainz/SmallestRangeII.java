package com.rainz;

import java.util.Arrays;

/*
 * Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).
 * After this process, we have some array B.
 * Return the smallest possible difference between the maximum value of B and the minimum value of B.
 */
public class SmallestRangeII {
    public static void test(String args[]) {
        int[] input1 = {1};
        System.out.println(smallestRangeII(input1, 0));
        int[] input2 = {0, 10};
        System.out.println(smallestRangeII(input2, 2));
        int[] input3 = {1, 3, 6};
        System.out.println(smallestRangeII(input3, 3));
    }
    // Let D = Max - Min. If K >= D, we must add the same change to every number, or else new range will exceed D.
    // Else, loop thru the array to find the best split such that 1st part of the array gets +K, and the rest gets -K.
    public static int smallestRangeII(int[] A, int K) {
        Arrays.sort(A);
        int N = A.length;
        int range = A[N-1] - A[0];
        if (K >= range) {
            // Only possibility is add all K or -K
            // Otherwise, min+K >= max, max-K <= min, new range will exceed current range
            return range;
        }
        // Find the best "split" point i where first i numbers needs +K and rest needs -K
        int ans = range;
        for (int i = 1; i < N; ++i) {
            int min = Math.min(A[0]+K, A[i]-K);
            int max = Math.max(A[i-1]+K, A[N-1]-K);
            int newRange = max - min;
            if (newRange < ans)
                ans = newRange;
        }
        return ans;
    }
}
