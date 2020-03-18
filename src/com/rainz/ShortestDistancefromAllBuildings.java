package com.rainz;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
public class ShortestDistancefromAllBuildings {
    public static void test(String args[]) {
        int[][] input1 = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        System.out.println(shortestDistance(input1));
        int[][] input2 = {{1,2,0}};
        System.out.println(shortestDistance(input2));
        int[][] input3 = {{0,2,1},{1,0,2},{0,1,0}};
        System.out.println(shortestDistance(input3));
    }

    public static int shortestDistance(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int[][] totalDists = new int[R][C];
        for (int rb = 0; rb < R; ++rb) {
            for (int cb = 0; cb < C; ++cb) {
                if (grid[rb][cb] != 1)
                    continue;
                int[][] dists = new int[R][C];
                for (int[] row: dists)
                    Arrays.fill(row, Integer.MAX_VALUE);
                Queue<int[]> workQ = new LinkedList<>();
                int[] building = {rb, cb};
                dists[rb][cb] = 0;
                workQ.add(building);
                while (!workQ.isEmpty()) {
                    int[] cell = workQ.poll();
                    int r = cell[0], c = cell[1];
                    for (int[] d: dirs) {
                        int rr = r + d[0], cc = c + d[1];
                        if (rr < 0 || rr >= R || cc < 0 || cc >= C || grid[rr][cc] != 0)
                            continue;
                        if (dists[rr][cc] <= dists[r][c] + 1)
                            continue;
                        dists[rr][cc] = dists[r][c] + 1;
                        int[] nb = {rr, cc};
                        workQ.add(nb);
                    }
                }
                for (int r = 0; r < R; ++r) {
                    for (int c = 0; c < C; ++c) {
                        // Note the special handling of non-reachable cells below
                        if (grid[r][c] == 0 && dists[r][c] != Integer.MAX_VALUE && totalDists[r][c] != Integer.MAX_VALUE )
                            totalDists[r][c] += dists[r][c];
                        else
                            totalDists[r][c] = Integer.MAX_VALUE;
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] == 0 && totalDists[r][c] < ans)
                    ans = totalDists[r][c];
            }
        }
        if (ans == Integer.MAX_VALUE)
            ans = -1;
        return ans;
    }
}