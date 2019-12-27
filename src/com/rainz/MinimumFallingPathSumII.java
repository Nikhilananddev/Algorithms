package com.rainz;

/*
 * Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one element from each row of arr, such that no two elements chosen in adjacent rows are in the same column.
 * Return the minimum sum of a falling path with non-zero shifts.
 */

public class MinimumFallingPathSumII {
    public static void test(String args[]) {
        int[][] input = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(minFallingPathSum(input));
    }

    public static int minFallingPathSum(int[][] arr) {
        int rows = arr.length;
        if (rows == 0)
            return 0;
        int cols = arr[0].length;
        if (cols < 2)
            return 0;
        int prevIdx = 0; // smallest pathSum idx in prev row
        int[] prevVals = new int[2]; // smallest 2 pathSums in prev row
        for (int r = rows-1; r >= 0; --r) {
            int[] row = arr[r];
            int minIdx = -1;
            int[] minVals = {Integer.MAX_VALUE, Integer.MAX_VALUE};
            for (int c = 0; c < cols; ++c) {
                int prevMin = (c != prevIdx ? prevVals[0] : prevVals[1]);
                int pathSum = row[c] + prevMin;
                if (minIdx < 0 || pathSum <= minVals[0]) {
                    minVals[1] = minVals[0];
                    minVals[0] = pathSum;
                    minIdx = c;
                } else if (pathSum < minVals[1]) {
                    minVals[1] = pathSum;
                }
            }
            prevIdx = minIdx;
            prevVals = minVals;
        }

        return prevVals[0];
    }
}
