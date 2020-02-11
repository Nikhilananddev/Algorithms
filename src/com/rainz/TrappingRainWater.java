package com.rainz;

/**
 * Created by Yu on 2/3/2015.
 */

/*
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 */
public class TrappingRainWater {
    public static void test(String args[]) {
        int[] test = {0,1,0,2,1,0,1,3,2,1,2,1};
        //int[] test = {4,2,3};
        System.out.println(trap(test));
    }

    public static int trap(int[] height) {
        int ans = 0, mx = 0, N = height.length;
        int[] maxLeft = new int[N];
        // Find max left for all
        for (int i = 0; i < N; ++i) {
            maxLeft[i] = mx;
            mx = Math.max(mx, height[i]);
        }
        // Now go from right to left
        mx = 0;
        for (int i = N - 1; i >= 0; --i) {
            int mn = Math.min(maxLeft[i], mx);
            mx = Math.max(mx, height[i]); // max from right
            if (mn > height[i])
                ans += mn - height[i];
        }
        return ans;
    }

    public static int trapOldPass(int[] A) {
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
