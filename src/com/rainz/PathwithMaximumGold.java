package com.rainz;

public class PathwithMaximumGold {
    public static void test(String args[]) {
        int[][] input1 = {{0,6,0},{5,8,7},{0,9,0}};
        System.out.println(getMaximumGold(input1));
        int[][] input2 = {{1,0,7},{2,0,6},{3,4,5},{0,3,0},{9,0,20}};
        System.out.println(getMaximumGold(input2));
    }

    private static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int dfs(int[][] grid, int r, int c, boolean[][] visited) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length
                || visited[r][c] || grid[r][c] == 0)
            return 0;
        visited[r][c] = true;
        int maxGold = 0;
        for (int[] dir: dirs) {
            int nextR = r + dir[0];
            int nextC = c + dir[1];
            int gold = dfs(grid, nextR, nextC, visited);
            if (gold > maxGold)
                maxGold = gold;
        }
        visited[r][c] = false;
        return grid[r][c] + maxGold;
    }

    public static int getMaximumGold(int[][] grid) {
        int result = 0;
        int rows = grid.length;
        if (rows == 0)
            return 0;
        int cols = grid[0].length;
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (grid[r][c] == 0)
                    continue;
                boolean[][] visited = new boolean[rows][cols];
                int maxGold = dfs(grid, r, c, visited);
                if (maxGold > result)
                    result = maxGold;
            }
        }
        return result;
    }
}
