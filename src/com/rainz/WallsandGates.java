package com.rainz;

import java.util.*;

/*
 * You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 */
public class WallsandGates {
    public static void test(String args[]) {
        int[][] input1 = new int[4][4];
        for (int[] row: input1)
            Arrays.fill(row, Integer.MAX_VALUE);
        input1[0][1] = -1;
        input1[1][3] = -1;
        input1[2][1] = -1;
        input1[2][3] = -1;
        input1[3][1] = -1;
        input1[0][2] = 0;
        input1[3][0] = 0;
        wallsAndGates(input1);
        Main.printArray2D(input1);
    }
    public static void wallsAndGates(int[][] rooms) {
        int R = rooms.length;
        if (R == 0)
            return;
        int C = rooms[0].length;
        Queue<int[]> workQ = new LinkedList<>();
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (rooms[r][c] == 0) {
                    int[] gate = {r, c};
                    workQ.add(gate);
                }
            }
        }
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!workQ.isEmpty()) {
            int[] cell = workQ.poll();
            int r = cell[0], c = cell[1];
            int val = rooms[r][c];
            for (int[] d: dirs) {
                int rr = r + d[0], cc = c + d[1];
                if (rr < 0 || rr >= R || cc < 0 || cc >= C || rooms[rr][cc] == -1)
                    continue;
                if (val + 1 >= rooms[rr][cc])
                    continue;
                rooms[rr][cc] = val + 1;
                int[] next = {rr, cc};
                workQ.offer(next);
            }
        }
    }
}
