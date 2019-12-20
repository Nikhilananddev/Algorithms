package com.rainz;

import java.util.LinkedList;
import java.util.Queue;

public class IslandPerimeter {
    public static void test(String args[]) {
        int[][] input = {{0,1,0,0},
                         {1,1,1,0},
                         {0,1,0,0},
                         {1,1,0,0}};
        System.out.println(islandPerimeter(input));
        int[][] input2 = {{1,1},{1,1}};
        System.out.println(islandPerimeter(input2));
    }

    public static int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        if (rows == 0)
            return 0;
        int cols = grid[0].length;
        int[] start = null;
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (grid[r][c] == 1) {
                    int[] p = {r, c};
                    start = p;
                    break;
                }
            }
            if (start != null)
                break; // need to break again out of outer loop
        }
        if (start == null)
            return 0;

        final int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int result = 0;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> workQ = new LinkedList<>();
        workQ.add(start);
        while (!workQ.isEmpty()) {
            int[] cell = workQ.remove();
            int r = cell[0];
            int c = cell[1];
            if (visited[r][c])
                continue;
            for (int[] dir: dirs) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                if (newR < 0 || newR >= rows || newC < 0 || newC >= cols) {
                    ++result; // out of bound
                    continue;
                }
                if (grid[newR][newC] == 0) {
                    ++result; // water
                    continue;
                }
                int[] newCell = {newR, newC};
                workQ.add(newCell);
            }
            visited[r][c] = true;
        }
        return result;
    }
}
