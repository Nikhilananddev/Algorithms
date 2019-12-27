package com.rainz;

/*
 * Given a square array of integers A, we want the minimum sum of a falling path through A.
 * A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.
 */
public class MinimumFallingPathSum {
    public static void test(String args[]) {
        int[][] input = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(minFallingPathSum(input));
    }

    public static int minFallingPathSum(int[][] A) {
        int rows = A.length;
        if (rows == 0)
            return 0;
        int cols = A[0].length;
        int[][] dp = new int[rows][cols];
        for (int c = 0; c < cols; ++c)
            dp[rows-1][c] = A[rows-1][c];
        for (int r = rows-2; r >= 0; --r) {
            for (int c = 0; c < cols; ++c) {
                int minPrevRow = dp[r + 1][c];
                if (c > 0)
                    minPrevRow = Math.min(minPrevRow, dp[r + 1][c - 1]);
                if (c < cols - 1)
                    minPrevRow = Math.min(minPrevRow, dp[r + 1][c + 1]);
                dp[r][c] = A[r][c] + minPrevRow;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i: dp[0])
            if (i < ans)
                ans = i;

        return ans;
    }

}
