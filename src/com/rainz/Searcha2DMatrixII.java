package com.rainz;

/*
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 */
public class Searcha2DMatrixII {
    public static void test(String args[]) {
        int[][] input1 = {
                           {1,   4,  7, 11, 15},
                           {2,   5,  8, 12, 19},
                           {3,   6,  9, 16, 22},
                           {10, 13, 14, 17, 24},
                           {18, 21, 23, 26, 30}
                         };
        System.out.println(searchMatrix(input1, 5));
        System.out.println(searchMatrix(input1, 20));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int R = matrix.length;
        if (R == 0)
            return false;
        int C = matrix[0].length;
        // Start point is upper right corner
        int r = 0, c = C - 1;
        while (r >= 0 && r < R && c >= 0 && c < C) {
            int curr = matrix[r][c];
            if (target == curr)
                return true;
            if (target > curr)
                ++r;
            else
                --c;
        }
        return false;
    }
}
