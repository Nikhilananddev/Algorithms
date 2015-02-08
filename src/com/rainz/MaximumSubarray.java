package com.rainz;

/**
 * Created by Yu on 2/8/2015.
 */
public class MaximumSubarray {
    public static void test(String args[]) {
        int[] test = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(test));
    }

    public static int maxSubArray(int[] A) {
        int maxSoFar = Integer.MIN_VALUE;
        int maxEnding = 0;
        for (int i = 0; i < A.length; ++i) {
            if (maxEnding > 0)
                maxEnding += A[i];
            else
                maxEnding = A[i];
            if (maxEnding > maxSoFar)
                maxSoFar = maxEnding;
        }
        return maxSoFar;
    }
}
