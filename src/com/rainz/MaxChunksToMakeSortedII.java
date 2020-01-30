package com.rainz;

/*
 * Given an array arr of integers (not necessarily distinct), we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.
 * What is the most number of chunks we could have made?
 */
public class MaxChunksToMakeSortedII {
    public static void test(String args[]) {
        int[] input1 = {5,4,3,2,1};
        System.out.println(maxChunksToSorted(input1));
        int[] input2 = {2,1,3,4,4};
        System.out.println(maxChunksToSorted(input2));
        int[] input3 = {0,0,1,1,1};
        System.out.println(maxChunksToSorted(input3));
    }

    public static int maxChunksToSorted(int[] arr) {
        int ans = 0;
        int N = arr.length;
        int[] maxFromLeft = new int[N];
        int[] minFromRight = new int[N];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; ++i) {
            int n = arr[i];
            if (n > max)
                max = n;
            maxFromLeft[i] = max;
        }
        int min = Integer.MAX_VALUE;
        for (int i = N-1; i >= 0; --i) {
            int n = arr[i];
            if (n < min)
                min = n;
            minFromRight[i] = min;
        }

        for (int i = 0; i < N; ++i) {
            if (i == N-1 || maxFromLeft[i] <= minFromRight[i+1]) {
                ++ans;
            }
        }
        return ans;
    }
}
