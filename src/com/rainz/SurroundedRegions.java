package com.rainz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SurroundedRegions {
    public static void test(String args[]) {
        char[][] input1 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };
        solve(input1);
        Main.printArray2D(input1);
    }

    public static void solve(char[][] board) {
        int R = board.length;
        if (R == 0)
            return;
        int C = board[0].length;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        boolean[][] visited = new boolean[R][C];
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (visited[r][c] || board[r][c] == 'X')
                    continue;
                Queue<int[]> workQ = new LinkedList<>();
                int[] cell = {r, c};
                workQ.add(cell);
                List<int[]> Os = new ArrayList<>();
                boolean surrounded = true;
                while (!workQ.isEmpty()) {
                    int[] curr = workQ.remove();
                    if (visited[curr[0]][curr[1]] || board[curr[0]][curr[1]] == 'X')
                        continue;
                    Os.add(curr);
                    visited[curr[0]][curr[1]] = true;
                    for (int[] dir: dirs) {
                        int newR = curr[0] + dir[0];
                        int newC = curr[1] + dir[1];
                        if (newR < 0 || newR >= R || newC < 0 || newC >= C) {
                            surrounded = false;
                            continue; // we still want to continue marking other O's as visited
                        }
                        int[] newCell = {newR, newC};
                        workQ.add(newCell);
                    }
                }
                if (surrounded)
                    for (int[] curr: Os)
                        board[curr[0]][curr[1]] = 'X';
            }
        }
    }
}
