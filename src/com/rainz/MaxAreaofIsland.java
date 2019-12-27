package com.rainz;

import java.util.LinkedList;
import java.util.Queue;

public class MaxAreaofIsland {
    public static void test(String args[]) {
        int[][] input1 = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                          {0,0,0,0,0,0,0,1,1,1,0,0,0},
                          {0,1,1,0,1,0,0,0,0,0,0,0,0},
                          {0,1,0,0,1,1,0,0,1,0,1,0,0},
                          {0,1,0,0,1,1,0,0,1,1,1,0,0},
                          {0,0,0,0,0,0,0,0,0,0,1,0,0},
                          {0,0,0,0,0,0,0,1,1,1,0,0,0},
                          {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(maxAreaOfIsland(input1));
        int[][] input2 = {{0,0,0,0,0,0,0,0}};
        System.out.println(maxAreaOfIsland(input2));

    }

    public static int maxAreaOfIsland(int[][] grid) {
        int R = grid.length;
        if (R == 0)
            return 0;
        int C = grid[0].length;
        int ans = 0;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        boolean[][] visited = new boolean[R][C];
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                int area = 0;
                if (visited[r][c] || grid[r][c] == 0)
                    continue;
                Queue<int[]> workQ = new LinkedList<>();
                int[] cell = {r, c};
                workQ.add(cell);
                while (!workQ.isEmpty()) {
                    int[] curr = workQ.remove();
                    if (visited[curr[0]][curr[1]] || grid[curr[0]][curr[1]] == 0)
                        continue;
                    ++area;
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
                if (area > ans)
                    ans = area;
            }
        }

        return ans;
    }
}
