package com.rainz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.
 */
public class SorttheMatrixDiagonally {
    public static void test(String args[]) {
        int[][] input1 = {{3,3,1,1},{2,2,1,2},{1,1,1,2}};
        Main.printArray2D(diagonalSort(input1));
    }
    
    public static int[][] diagonalSort(int[][] mat) {
        int R = mat.length;
        if (R == 0)
            return mat;
        int C = mat[0].length;
        // Add start of each diagonal
        int[][] starts = new int[R+C-1][2];
        int idx = 0;
        for (int r = R-1; r >= 0; --r) {
            starts[idx][0] = r;
            starts[idx][1] = 0;
            ++idx;
        }
        for (int c = 1; c < C; ++c) {
            starts[idx][0] = 0;
            starts[idx][1] = c;
            ++idx;
        }
        for (int[] start: starts) {
            List<Integer> l = new ArrayList<>();
            int r = start[0], c = start[1];
            while (r < R && c < C) {
                l.add(mat[r][c]);
                ++r; ++c;
            }
            Collections.sort(l);
            r = start[0];
            c = start[1];
            int i = 0;
            while (r < R && c < C) {
                mat[r][c] = l.get(i);
                ++r; ++c; ++i;
            }
        }
        return mat;
    }
}
