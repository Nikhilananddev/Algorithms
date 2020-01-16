package com.rainz;

/*
 * On a 2-dimensional grid, there are 4 types of squares:
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 */

public class UniquePathsIII {
    public static void test(String args[]) {
        int[][] input1 = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        System.out.println(uniquePathsIII(input1));
        int[][] input2 = {{1,0,0,0},{0,0,0,0},{0,0,0,2}};
        System.out.println(uniquePathsIII(input2));
        int[][] input3 = {{0,1},{2,0}};
        System.out.println(uniquePathsIII(input3));
    }

    private static int dfs(int[][] grid, int r, int c, boolean[][] visited, int numEmpty, int ways) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length)
            return ways; // out of bound
        if (grid[r][c] == -1 || visited[r][c])
            return ways; // obstacle, or visited
        --numEmpty;
        if (numEmpty < 0)
            return ways; // out of steps
        if (grid[r][c] == 2) {
            if (numEmpty == 0)
                return ways + 1; // reached destination
            return ways;
        }
        visited[r][c] = true;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] d: dirs) {
            ways = dfs(grid, r + d[0], c + d[1], visited, numEmpty, ways);
        }
        visited[r][c] = false;
        return ways;
    }

    public static int uniquePathsIII(int[][] grid) {
        int R = grid.length;
        if (R == 0)
            return 0;
        int C = grid[0].length;
        int rStart = 0, cStart = 0;
        int numEmpty = 0;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] == 1) {
                    rStart = r;
                    cStart = c;
                }
                if (grid[r][c] != -1)
                    ++numEmpty;
            }
        }

        boolean[][] visited = new boolean[R][C];
        int ans = dfs(grid, rStart, cStart, visited, numEmpty, 0);
        return ans;
    }
}
