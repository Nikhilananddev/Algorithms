package com.rainz;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathinBinaryMatrix {
    public static void test(String args[]) {
        int[][] input1 = {{0,1},{1,0}};
        System.out.println(shortestPathBinaryMatrix(input1));
        int[][] input2 = {{0,0,0},{1,1,0},{1,1,0}};
        System.out.println(shortestPathBinaryMatrix(input2));
    }

    public static int shortestPathBinaryMatrix(int[][] grid) {
        final int[][] dirs8 = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        int R = grid.length;
        if (R == 0)
            return 0;
        int C = grid[0].length;
        if (grid[R-1][C-1] == 1)
            return -1;
        int[][] shortest = new int[R][C];
        for (int[] r: shortest)
            Arrays.fill(r, -1);
        Queue<int[]> workQ = new LinkedList<>();
        shortest[R-1][C-1] = 1;
        int[] lr = {R - 1, C - 1};
        workQ.add(lr);
        while (!workQ.isEmpty()) {
            int[] cell = workQ.remove();
            int r = cell[0], c = cell[1];
            for (int[] dir: dirs8) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                if (newR < 0 || newR >= R || newC < 0 || newC >= C || grid[newR][newC] != 0)
                    continue;
                int newShortest = shortest[newR][newC];
                int newDist = shortest[r][c] + 1;
                if (newShortest == -1 || newShortest > newDist) {
                    shortest[newR][newC] = newDist;
                    int[] newCell = {newR, newC};
                    workQ.add(newCell);
                }
            }
        }

        return shortest[0][0];
    }
}
