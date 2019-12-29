package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 * Return the intersection of these two interval lists.
 */

public class IntervalListIntersections {
    public static void test(String args[]) {
        int[][] A = {{0,2},{5,10},{13,23},{24,25}};
        int[][] B = {{1,5},{8,12},{15,24},{25,26}};
        Main.printArray2D(intervalIntersection(A, B));
    }

    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> output = new ArrayList<>();
        int aIdx = 0;
        int bIdx = 0;
        while (aIdx < A.length && bIdx < B.length) {
            int[] a = A[aIdx];
            int[] b = B[bIdx];
            int left = Math.max(a[0], b[0]);
            int right = Math.min(a[1], b[1]);
            if (left <= right) {
                int[] inter = {left, right};
                output.add(inter);
            }
            if (a[1] > b[1])
                ++bIdx;
            else if (a[1] < b[1])
                ++aIdx;
            else {
                // a[1] == b[1]
                ++aIdx;
                ++bIdx;
            }
        }

        int[][] ans = new int[output.size()][2];
        for (int i = 0; i < ans.length; ++i) {
            ans[i][0] = output.get(i)[0];
            ans[i][1] = output.get(i)[1];
        }
        return ans;
    }
}
