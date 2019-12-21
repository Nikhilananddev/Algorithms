package com.rainz;

/*
 * On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.
 * Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.
 * Now, we walk in a clockwise spiral shape to visit every position in this grid.
 * Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.)
 * Eventually, we reach all R * C spaces of the grid.
 * Return a list of coordinates representing the positions of the grid in the order they were visited.
 */

public class SpiralMatrixIII {
    public static void test(String args[]) {
        Main.printArray2D(spiralMatrixIII(1, 4, 0, 0));
        Main.printArray2D(spiralMatrixIII(5, 6, 1, 4));
    }

    public static int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0; // we initially faces east

        int[][] result = new int[R*C][2];
        int outIdx = 0;
        int stepLen = 1;
        int currR = r0;
        int currC = c0;
        result[outIdx][0] = currR;
        result[outIdx++][1] = currC;
        boolean done = false;
        while (!done && outIdx < result.length) {
            // Walk twice
            for (int i = 0; !done && i < 2; ++i) {
                for (int d = 0; !done && d < stepLen; ++d) {
                    currR += dirs[dir][0];
                    currC += dirs[dir][1];
                    if (currR >= 0 && currR < R &&
                        currC >= 0 && currC < C) {
                        result[outIdx][0] = currR;
                        result[outIdx++][1] = currC;
                        if (outIdx >= result.length)
                            done = true;
                    }
                }
                dir = (dir + 1) % 4;
            }
            ++stepLen;
        }
        return result;
    }

}
