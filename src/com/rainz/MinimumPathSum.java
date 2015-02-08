package com.rainz;

/**
 * Created by Yu on 2/8/2015.
 */
public class MinimumPathSum {
    public static void test(String args[]) {
        int[][] test = {
                {1, 3, 5, 2, 4, 3},
                {6, 2, 1, 4, 1, 7},
                {2, 7, 1, 1, 4, 2}
        };
        System.out.println(minPathSum(test));
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0)
            return 0;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = grid[m-1][n-1];
        for (int i = n - 2; i >= 0; --i) {
            dp[m-1][i] = grid[m-1][i] + dp[m-1][i+1];
        }
        for (int i = m - 2; i >= 0; --i) {
            dp[i][n-1] = grid[i][n-1] + dp[i+1][n-1];
        }
        for (int row = m - 2; row >= 0; --row) {
            for (int col = n - 2; col >= 0; --col) {
                int dpBelow = dp[row + 1][col];
                int dpRight = dp[row][col + 1];
                int minDp = dpBelow < dpRight ? dpBelow : dpRight;
                dp[row][col] = grid[row][col] + minDp;
            }
        }
        /*for (int[] r: dp) {
            for (int x: r) {
                System.out.printf("%d ", x);
            }
            System.out.println();
        }*/
        return dp[0][0];
    }
}
