package com.rainz;

/*
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.
 */
public class MinimumSwapstoGroupAll1sTogether {
    public static void test(String args[]) {
        int[] input1 = {1,0,1,0,1};
        System.out.println(minSwaps(input1));
        int[] input2 = {0,0,0,1,0};
        System.out.println(minSwaps(input2));
        int[] input3 = {1,0,1,0,1,0,0,1,1,0,1};
        System.out.println(minSwaps(input3));
    }
    /*
     * If we swap all 1s together, it'll form a window with size equals # all 1s in input.
     * So, we use a sliding window of that size and find the window containing max 1s.
     * Then # 0s in this window is the min swap.
     */
    public static int minSwaps(int[] data) {
        // Get 1s count and make that window size.
        int winSize = 0;
        for (int n: data)
            if (n == 1)
                ++winSize;
        // Count 1s in initial window
        int oneCount = 0;
        int maxCount = 0;
        for (int i = 0; i < winSize; ++i)
            if (data[i] == 1)
                ++oneCount;
        maxCount = oneCount;
        // Slide window right and find window with max 1s
        for (int i = 1; i + winSize <= data.length; ++i) {
            if (data[i-1] == 1)
                --oneCount;
            if (data[i+winSize-1] == 1)
                ++oneCount;
            if (oneCount > maxCount)
                maxCount = oneCount;
        }
        // winSize-maxCount is the min # 0s we need to swap.
        return winSize - maxCount;
    }
}
