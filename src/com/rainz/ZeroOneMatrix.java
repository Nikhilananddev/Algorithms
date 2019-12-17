package com.rainz;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix {
    /*
     * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
     * The distance between two adjacent cells is 1.
     */
    public static void test(String args[]) {
        int[][] input1 = {{0,0,0},
                          {0,1,0},
                          {0,0,0}};
        int[][] input2 = {{0,0,0},
                          {0,1,0},
                          {1,1,1}};
        Main.printArray2D(updateMatrix(input1));
        Main.printArray2D(updateMatrix(input2));
    }

    public static int[][] updateMatrix(int[][] matrix) {
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;
        int[][] result = new int[rows][cols];
        for (int[] row: result)
            Arrays.fill(row, Integer.MAX_VALUE);

        Queue<int[]> workQ = new LinkedList<>();
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (matrix[r][c] == 0) {
                    int[] cell = {r, c};
                    workQ.add(cell);
                    result[r][c] = 0;
                }
            }
        }
        while (!workQ.isEmpty()) {
            int[] cell = workQ.remove();
            for (int[] dir: dirs) {
                int newR = cell[0] + dir[0];
                int newC = cell[1] + dir[1];
                if (newR < 0 || newR >= rows || newC < 0 || newC >= cols)
                    continue;
                int newDist = result[cell[0]][cell[1]] + 1;
                if (result[newR][newC] <= newDist)
                    continue;
                result[newR][newC] = newDist;
                int[] newCell = {newR, newC};
                workQ.add(newCell);
            }
        }
        return result;
    }
}
