package com.rainz;

import java.util.Arrays;

public class NonoverlappingIntervals {
    public static void test(String args[]) {
        int[][] input1 = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println(eraseOverlapIntervals(input1));
        int[][] input2 = {{1,2},{1,2},{1,2}};
        System.out.println(eraseOverlapIntervals(input2));
        int[][] input3 = {{1,2},{2,3}};
        System.out.println(eraseOverlapIntervals(input3));

    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        int ans = 0;
        Arrays.sort( intervals,
                (x, y) -> x != y ? Integer.compare(x[0], y[0]) : Integer.compare(x[1], y[1]) );
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; ++i) {
            int[] curr = intervals[i];
            if (curr[0] >= prev[1]) {
                prev = curr;
                continue; // not overlapping
            }
            ++ans;
            if (curr[1] <= prev[1])
                prev = curr;
        }
        return ans;
    }
}
