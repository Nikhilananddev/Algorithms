package com.rainz;

/*
 * Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.
 * What is the most number of chunks we could have made?
 */
public class MaxChunksToMakeSorted {
    public static void test(String args[]) {
        int[] input1 = {4,3,2,1,0};
        System.out.println(maxChunksToSorted(input1));
        int[] input2 = {1,0,2,3,4};
        System.out.println(maxChunksToSorted(input2));
    }

    /*
     * Solution has to do with i<->arr[i] mapping.
     * If arr[i] > i, we have to extend chunk range to at least arr[i].
     * In other words, index of right end of chunk K is the max in the chunk
     */
    public static int maxChunksToSorted(int[] arr) {
        int result = 0;
        int chunkStart = -1;
        int chunkEnd = -1;

        for (int i = 0; i < arr.length; ++i) {
            if (chunkStart < 0) {
                // When there's no chunk, we'll never see i < arr[i] because:
                // If i = 0, i will always be <= arr[i]
                // If i > 0, it'll be skipped when we have a chunk
                if (i == arr[i])
                    ++result; // this single number is in correct place and can be a chunk by itself
                else {
                    // Here i > arr[i].
                    chunkStart = i;
                    chunkEnd = arr[i];
                }
            } else {
                if (arr[i] > chunkEnd)
                    chunkEnd = arr[i]; // larger value found, extend chunk range
                else if (i == chunkEnd) {
                    // End of current chunk reached
                    ++result;
                    chunkStart = -1;
                    chunkEnd = -1;
                }
            }
        }
        // Record the last chunk
        if (chunkStart >= 0)
            ++result;

        return result;
    }
}
