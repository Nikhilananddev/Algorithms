package com.rainz;

/**
 * Created by Yu on 2/8/2015.
 */

/*
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 */

public class UniquePathsII {
    public static void test(String args[]) {
        int[][] test = {
                {0,0,0,0,0},
                {0,0,0,0,1},
                {0,0,1,0,0}
        };
        System.out.println(uniquePathsWithObstacles(test));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0)
            return 0;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = obstacleGrid[m-1][n-1] == 0 ? 1 : 0;
        for (int i = n - 2; i >= 0; --i) {
            dp[m-1][i] = obstacleGrid[m-1][i] == 0 ? dp[m-1][i+1] : 0;
        }
        for (int i = m - 2; i >= 0; --i) {
            dp[i][n-1] = obstacleGrid[i][n-1] == 0 ? dp[i+1][n-1] : 0;
        }
        for (int row = m - 2; row >= 0; --row) {
            for (int col = n - 2; col >= 0; --col)
                dp[row][col] = obstacleGrid[row][col] == 0 ? dp[row+1][col] + dp[row][col+1] : 0;
        }
        return dp[0][0];
    }
}
