package com.rainz;

/**
 * Created by Yu on 2/8/2015.
 */
public class UniquePaths {
    public static void test(String args[]) {
        System.out.println(uniquePaths(3, 5));
    }

    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = 1;
        for (int i = n - 2; i >= 0; --i) {
            dp[m-1][i] = 1;
        }
        for (int i = m - 2; i >= 0; --i) {
            dp[i][n-1] = 1;
        }
        for (int row = m - 2; row >= 0; --row) {
            for (int col = n - 2; col >= 0; --col)
                dp[row][col] = dp[row+1][col] + dp[row][col+1];
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
