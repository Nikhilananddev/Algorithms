package com.rainz;

/*
 * Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.
 */

public class Largest1BorderedSquare {
    public static void test(String args[]) {
        int[][] input1 = {{1,1,1},{1,0,1},{1,1,1}};
        System.out.println(largest1BorderedSquare(input1));
        int[][] input2 = {{1,1,0,0}};
        System.out.println(largest1BorderedSquare(input2));
    }

    public static int largest1BorderedSquare(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int[][][] runs = new int[R][C][2]; // right, down
        // Scan rows and store right run length for each cell
        for (int r = 0; r < R; ++r) {
            int runStart = -1;
            for (int c = 0; c <= C; ++c) {
                int cell = c < C ? grid[r][c] : 0;
                if (cell == 1) {
                    if (runStart < 0)
                        runStart = c;
                } else {
                    if (runStart >= 0) {
                        for (int i = c-1; i >= runStart; --i)
                            runs[r][i][0] = c - i; // right;
                        runStart = -1;
                    }
                }
            }
        }
        // Scan columns and store down run length for each cell
        for (int c = 0; c < C; ++c) {
            int runStart = -1;
            for (int r = 0; r <= R; ++r) {
                int cell = r < R ? grid[r][c] : 0;
                if (cell == 1) {
                    if (runStart < 0)
                        runStart = r;
                } else {
                    if (runStart >= 0) {
                        for (int i = r-1; i >= runStart; --i)
                            runs[i][c][1] = r - i; // right;
                        runStart = -1;
                    }
                }
            }
        }

        // Now scan each cell and find max square using "runs" data
        int ans = 0;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                int len = Math.min(runs[r][c][0], runs[r][c][1]);
                if (len == 0)
                    continue;
                int squareSide = 1;
                for (int side = len; side > 1; --side) {
                    // Check right edge
                    int cRight = c + side - 1;
                    if (runs[r][cRight][1] < side)
                        continue;
                    // Check bottom edge
                    int rDown = r + side - 1;
                    if (runs[rDown][c][0] < side)
                        continue;
                    squareSide = side;
                    break;
                }
                if (squareSide > ans)
                    ans = squareSide;
            }
        }
        return ans*ans;
    }
}
