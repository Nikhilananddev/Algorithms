package com.rainz;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized and return the distance.
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 * If no land or water exists in the grid, return -1.
 */
public class AsFarfromLandasPossible {
    public static void test(String args[]) {
        int[][] input1 = {{1,0,1},{0,0,0},{1,0,1}};
        System.out.println(maxDistance(input1));
        int[][] input2 = {{1,0,0},{0,0,0},{0,0,0}};
        System.out.println(maxDistance(input2));
    }

    public static int maxDistance(int[][] grid) {
        int rows = grid.length;
        if (rows == 0)
            return -1;
        int cols = grid[0].length;
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        int maxDist = -1; // if there's no land or all lands, return this.
        int[][] distance = new int[rows][cols];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        Queue<int[]> workQ = new LinkedList<>();
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (grid[r][c] == 1) {
                    int[] cell = {r, c};
                    workQ.add(cell);
                    distance[r][c] = 0;
                }
            }
        }

        while (!workQ.isEmpty()) {
            int[] cell = workQ.remove();
            int r = cell[0];
            int c = cell[1];
            for (int[] dir: dirs) {
                int newR = r + dir[0];
                if (newR < 0 || newR >= rows)
                    continue;
                int newC = c + dir[1];
                if (newC < 0 || newC >= cols)
                    continue;
                if (grid[newR][newC] == 1)
                    continue; // no need to queue this land cell again
                int newDist = distance[r][c] + 1;
                if (newDist < distance[newR][newC]) {
                    distance[newR][newC] = newDist;
                    if (newDist > maxDist)
                        maxDist = newDist;
                    int[] newCell = {newR, newC};
                    workQ.add(newCell);
                }
            }
        }
        return maxDist;
    }
}
