package com.rainz;

import java.util.Arrays;

/*
 * Given a list of intervals, remove all intervals that are covered by another interval in the list.
 * Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 * After doing so, return the number of remaining intervals.
 */

public class RemoveCoveredIntervals {
    public static void test(String args[]) {
        int[][] input = {{1,4},{3,6},{2,8}};
        System.out.println(removeCoveredIntervals(input));
    }

    public static int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals,
                (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        int result = 0;
        int maxEnd = -1;
        for (int[] curr: intervals) {
            if (maxEnd < 0 || curr[1] > maxEnd) {
                ++result; // curr not covered by last
                maxEnd = curr[1];
            }
        }
        return result;
    }

}
