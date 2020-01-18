package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 */

public class ShortestPathinaGridwithObstaclesElimination {
    public static void test(String args[]) {
        int[][] grid1 = {{0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}};
        System.out.println(shortestPath(grid1, 1));
        int[][] grid2 = {{0, 1, 1},
                {1, 1, 1},
                {1, 0, 0}};
        System.out.println(shortestPath(grid2, 1));
        int[][] grid3 = {{0,1,0,0,0,1,0,0},
                         {0,1,0,1,0,1,0,1},
                         {0,0,0,1,0,0,1,0}};
        System.out.println(shortestPath(grid3, 1));
        int[][] grid4 = {{0,1,0,0},
                         {0,1,0,1},
                         {0,0,1,0}};
        System.out.println(shortestPath(grid4, 1));
        int[][] grid5 = {{0,0,1,0,0,0,0,1,0,1,1,0,0,1,1},
                         {0,0,0,1,1,0,0,1,1,0,1,0,0,0,1},
                         {1,1,0,0,0,0,0,1,0,1,0,0,1,0,0},
                         {1,0,1,1,1,1,0,0,1,1,0,1,0,0,1},
                         {1,0,0,0,1,1,0,1,1,0,0,1,1,1,1},
                         {0,0,0,1,1,1,0,1,1,0,0,1,1,1,1},
                         {0,0,0,1,0,1,0,0,0,0,1,1,0,1,1},
                         {1,0,0,1,1,1,1,1,1,0,0,0,1,1,0},
                         {0,0,1,0,0,1,1,1,1,1,0,1,0,0,0},
                         {0,0,0,1,1,0,0,1,1,1,1,1,1,0,0},
                         {0,0,0,0,1,1,1,0,0,1,1,1,0,1,0}};
        System.out.println(shortestPath(grid5, 27));
    }

    public static int shortestPath(int[][] grid, int k) {
        int R = grid.length;
        int C = grid[0].length;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][][] visited = new boolean[R][C][k+1]; // 0..k obstacles
        List<int[]> currLevel = new ArrayList<>();
        int[] start = {0, 0, 0}; // row, col, # obstacles eliminated before current node
        currLevel.add(start);
        int steps = 0;
        while (!currLevel.isEmpty()) {
            List<int[]> nextLevel = new ArrayList<>();
            for (int[] node: currLevel) {
                int r = node[0], c = node[1], obs = node[2];
                if (r == R-1 && c == C-1)
                    return steps;
                if (r < 0 || r >= R || c < 0 || c >= C || visited[r][c][obs])
                    continue;
                visited[r][c][obs] = true;
                if (grid[r][c] == 1) {
                    if (obs >= k)
                        continue;
                    ++obs;
                }
                for (int[] dir: dirs) {
                    int[] nextCell = {r+dir[0], c+dir[1], obs};
                    nextLevel.add(nextCell);
                }
            }
            ++steps;
            currLevel = nextLevel;
        }
        return -1;
    }
}
