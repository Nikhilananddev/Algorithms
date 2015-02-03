package com.rainz;

/**
 * Created by Yu on 2/3/2015.
 */
public class TrappingRainWater {
    public static void test(String args[]) {
        int[] test = {0,1,0,2,1,0,1,3,2,1,2,1};
        //int[] test = {4,2,3};
        System.out.println(trap(test));
    }

    public static int trap(int[] A) {
        int maxIdx = 0;
        for (int i = 1; i < A.length; ++i) {
            if (A[i] > A[maxIdx])
                maxIdx = i;
        }
        int water = 0;
        int leftIdx = 0;
        for (int i = 1; i < maxIdx; ++i) {
            if (A[leftIdx] <= A[i])
                leftIdx = i;
            else
                water += A[leftIdx] - A[i];
        }
        int rightIdx = A.length - 1;
        for (int i = A.length - 2; i > maxIdx; --i) {
            if (A[rightIdx] <= A[i])
                rightIdx = i;
            else
                water += A[rightIdx] - A[i];
        }
        return water;
    }
}
