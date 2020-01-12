package com.rainz;

/*
 * Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, and add x to A[i].
 * After this process, we have some array B.
 * Return the smallest possible difference between the maximum value of B and the minimum value of B.
 */
public class SmallestRangeI {
    public static void test(String args[]) {
        int[] input1 = {1};
        System.out.println(smallestRangeI(input1, 0));
        int[] input2 = {0, 10};
        System.out.println(smallestRangeI(input2, 2));
        int[] input3 = {1, 3, 6};
        System.out.println(smallestRangeI(input3, 3));
    }
    public static int smallestRangeI(int[] A, int K) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int n: A) {
            if (n > max)
                max = n;
            if (n < min)
                min = n;
        }
        min += K;
        max -= K;
        if (min > max)
            min = max;
        return max-min;
    }
}
