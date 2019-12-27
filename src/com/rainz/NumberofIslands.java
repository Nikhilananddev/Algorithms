package com.rainz;

import java.util.LinkedList;
import java.util.Queue;

public class NumberofIslands {
    public static void test(String args[]) {
        char[][] input1 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'},
        };
        System.out.println(numIslands(input1));
        char[][] input2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'},
        };
        System.out.println(numIslands(input2));
    }

    public static int numIslands(char[][] grid) {
        int R = grid.length;
        if (R == 0)
            return 0;
        int C = grid[0].length;
        int ans = 0;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        boolean[][] visited = new boolean[R][C];
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (visited[r][c] || grid[r][c] == '0')
                    continue;
                Queue<int[]> workQ = new LinkedList<>();
                int[] cell = {r, c};
                workQ.add(cell);
                while (!workQ.isEmpty()) {
                    int[] curr = workQ.remove();
                    if (visited[curr[0]][curr[1]] || grid[curr[0]][curr[1]] == '0')
                        continue;
                    visited[curr[0]][curr[1]] = true;
                    for (int[] dir: dirs) {
                        int newR = curr[0] + dir[0];
                        int newC = curr[1] + dir[1];
                        if (newR < 0 || newR >= R || newC < 0 || newC >= C)
                            continue;
                        int[] newCell = {newR, newC};
                        workQ.add(newCell);
                    }
                }
                ++ans;
            }
        }

        return ans;
    }

}
