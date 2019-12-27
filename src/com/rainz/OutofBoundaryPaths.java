package com.rainz;

/*
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.
 */
public class OutofBoundaryPaths {
    public static void test(String args[]) {
        System.out.println(findPaths(2, 2, 2, 0, 0));
        System.out.println(findPaths(1, 3, 3, 0, 1));
        System.out.println(findPaths(10, 10, 0, 5, 5));
        System.out.println(findPaths(8, 7, 16, 1, 5));
    }

    public static int findPathsRecursive(int m, int n, int N, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >=n)
            return 1;
        if (N <= 0)
            return 0; // out of steps
        int ans = 0;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir: dirs) {
            ans += findPaths(m, n, N-1, i+dir[0], j+dir[1]);
            ans %= 1000000007;
        }
        return ans;
    }

    public static int findPaths(int m, int n, int N, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >=n)
            return 1;
        if (N <= 0)
            return 0;
        final int MOD_CONST = 1000000007;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[][][] dp = new int[m][n][N+1];
        // Base case: N=1. edges: dp=1; corners: dp=2
        for (int r = 0; r < m; ++r) {
            ++dp[r][0][1];
            ++dp[r][n-1][1];
        }
        for (int c = 0; c < n; ++c) {
            ++dp[0][c][1];
            ++dp[m-1][c][1];
        }

        for (int step = 2; step <= N; ++step) {
            for (int r = 0; r < m; ++r) {
                for (int c = 0; c < n; ++c) {
                    for (int[] dir : dirs) {
                        int newR = r + dir[0];
                        int newC = c + dir[1];
                        if (newR < 0 || newR >= m || newC < 0 || newC >= n)
                            continue;
                        dp[r][c][step] += dp[newR][newC][step - 1];
                        dp[r][c][step] %= MOD_CONST;
                    }
                }
            }
        }
        int ans = 0;
        for (int step = 1; step <= N; ++step) {
            ans += dp[i][j][step];
            ans %= MOD_CONST;
        }
        return ans;
    }
}
