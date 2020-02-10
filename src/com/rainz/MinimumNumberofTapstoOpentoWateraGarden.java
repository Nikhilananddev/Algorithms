package com.rainz;

import java.util.Arrays;
import java.util.Comparator;

/*
 * There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).
 * There are n + 1 taps located at points [0, 1, ..., n] in the garden.
 * Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.
 * Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.
 */
public class MinimumNumberofTapstoOpentoWateraGarden {
    public static void test(String args[]) {
        int[] input1 = {3,4,1,1,0,0};
        System.out.println(minTaps(5, input1));
        int[] input2 = {0,0,0,0};
        System.out.println(minTaps(3, input2));
        int[] input3 = {1,2,1,0,2,1,0,1};
        System.out.println(minTaps(7, input3));
        int[] input4 = {4,0,0,0,0,0,0,0,4};
        System.out.println(minTaps(8, input4));
        int[] input5 = {4,0,0,0,4,0,0,0,4};
        System.out.println(minTaps(8, input5));
    }

    public static int minTaps(int n, int[] ranges) {
        int[][] taps = new int[n+1][2];
        for (int i = 0; i <= n; ++i) {
            taps[i][0] = i - ranges[i];
            taps[i][1] = i + ranges[i];
        }
        Arrays.sort(taps, Comparator.comparingInt(x->x[0]));
        int idx = 0;
        int coverEnd = 0;
        int ans = 0;
        while (idx <= n && coverEnd < n) {
            // Among remaining taps which can cover left side, find the one which covers furthest to the right
            int maxRightIdx = -1;
            while (idx <= n && taps[idx][0] <= coverEnd) {
                if (maxRightIdx < 0 || taps[maxRightIdx][1] < taps[idx][1])
                    maxRightIdx = idx;
                ++idx;
            }
            if (maxRightIdx < 0)
                return -1; // no remaining taps can cover left side
            int nextEnd = taps[maxRightIdx][1];
            if (nextEnd <= coverEnd)
                return -1; // no remaining taps can reach further right than previous tap
            // Select this tap and update our cover range
            ++ans;
            coverEnd = nextEnd;
        }
        if (coverEnd < n)
            return -1;
        return ans;
    }
}
