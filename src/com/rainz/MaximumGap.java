package com.rainz;

/*
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Return 0 if the array contains less than 2 elements.
 */
public class MaximumGap {
    public static void test(String args[]) {
        int[] input1 = {3,6,9,1};
        System.out.println(maximumGap(input1));
        int[] input2 = {10};
        System.out.println(maximumGap(input2));
        int[] input3 = {1,1,1,1,1,5,5,5,5,5};
        System.out.println(maximumGap(input3));
    }

    /*
     * Compute min gap possible based on range.
     * Create buckets with len <= min gap and put numbers in buckets.
     * Only compute gaps between max and min of two buckets
     */
    public static int maximumGap(int[] nums) {
        int N = nums.length;
        if (N < 2)
            return 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int n: nums) {
            if (n > max)
                max = n;
            if (n < min)
                min = n;
        }
        int range = max - min;
        if (range == 0)
            return 0; // avoid division-by-zero
        int maxGap = 1;
        int bktLen = range / (N - 1); // max gap must be >= this value
        if (bktLen == 0)
            bktLen = 1; // avoid division-by-zero
        int bucketCount = range / bktLen + 1;
        int[][] buckets = new int[bucketCount][2]; // each bucket has min and max
        for (int[] bucket: buckets) {
            bucket[0] = -1; // no min/max
        }
        for (int n: nums) {
            int bkt = (n - min) / bktLen;
            int[] bucket = buckets[bkt];
            if (bucket[0] == -1 || n < bucket[0])
                bucket[0] = n; // min of bucket
            if (bucket[1] == -1 || n > bucket[1])
                bucket[1] = n; // max of bucket
        }
        int[] prevBkt = null;
        int bktId = 0;
        for (bktId = 0; bktId < buckets.length; ++bktId) {
            int[] bucket = buckets[bktId];
            if (bucket[0] == -1)
                continue;
            if (prevBkt != null) {
                int gap = bucket[0] - prevBkt[1];
                if (gap > maxGap)
                    maxGap = gap;
            }
            prevBkt = bucket;
        }
        return maxGap;
    }
}
