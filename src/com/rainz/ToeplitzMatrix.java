package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class ToeplitzMatrix {
    public static void test(String args[]) {
        int[][] matrix1 = {{1,2,3,4},
                           {5,1,2,3},
                           {9,5,1,2},};
        System.out.println(isToeplitzMatrix(matrix1));
        int[][] matrix2 = {{1,2}, {2,2}};
        System.out.println(isToeplitzMatrix(matrix2));
    }

    public static boolean isToeplitzMatrix(int[][] matrix) {
        int R = matrix.length;
        if (R == 0)
            return true;
        int C = matrix[0].length;
        List<int[]> startPoints = new ArrayList<>();
        for (int c = 0; c < C; ++c) {
            int[] p = {0, c};
            startPoints.add(p);
        }
        for (int r = 1; r < R; ++r) {
            int[] p = {r, 0};
            startPoints.add(p);
        }
        for (int[] p: startPoints) {
            int r = p[0];
            int c = p[1];
            int val = matrix[r][c];
            while (r >= 0 && r < R && c >= 0 && c < C) {
                if (matrix[r][c] != val)
                    return false;
                ++r;
                ++c;
            }
        }
        return true;
    }
}
