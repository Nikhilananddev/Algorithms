package com.rainz;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfClosedIslands {
    public static void test(String args[]) {
        // 0 is land, 1 is water
        int[][] input = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
        int[][] input2 = {{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}};
        int[][] input3 = {{1,1,1,1,1,1,1},
                          {1,0,0,0,0,0,1},
                          {1,0,1,1,1,0,1},
                          {1,0,1,0,1,0,1},
                          {1,0,1,1,1,0,1},
                          {1,0,0,0,0,0,1},
                          {1,1,1,1,1,1,1}};
        System.out.println(closedIsland(input));
        System.out.println(closedIsland(input2));
        System.out.println(closedIsland(input3));
    }

    private static boolean bfs(int[][] grid, boolean[][] visited, int row, int col) {
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        Queue<int[]> workQ = new LinkedList<>();
        int[] p = {row, col};
        workQ.add(p);
        boolean closed = true;
        while (!workQ.isEmpty()) {
            p = workQ.remove();
            visited[p[0]][p[1]] = true;
            for (int[] dir: dirs) {
                int r = p[0] + dir[0];
                int c = p[1] + dir[1];
                if (r < 0 || r >= grid.length ||
                    c < 0 || c >= grid[0].length) {
                    closed = false;
                } else if (grid[r][c] == 0 && !visited[r][c]){
                    int[] newP = {r, c};
                    workQ.add(newP);
                }
            }
        }
        return closed;
    }

    public static int closedIsland(int[][] grid) {
        if (grid.length == 0)
            return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int result = 0;
        for (int r = 0; r < grid.length; ++r) {
            int[] row = grid[r];
            for (int c = 0; c < row.length; ++c) {
                if (grid[r][c] == 0 && !visited[r][c]) {
                    if (bfs(grid, visited, r, c))
                        ++result;
                }
            }
        }
        return result;
    }
}
