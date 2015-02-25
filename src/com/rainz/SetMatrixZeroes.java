package com.rainz;

/**
 * Created by Yu on 2/24/2015.
 */
public class SetMatrixZeroes {
    public static void test(String args[]) {
        int[][] test = {
                /*{1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1}*/
                {0,0,0,5},
                {4,3,1,4},
                {0,1,1,4},
                {1,2,1,3},
                {0,0,1,1}
        };
        setZeroes(test);
        for (int r = 0; r < test.length; ++r) {
            for (int c = 0; c < test[0].length; ++c)
                System.out.print("" + test[r][c] + " ");
            System.out.println();
        }
    }

    public static void setZeroes(int[][] matrix) {
        boolean row0 = false, col0 = false;
        for (int r = 0; r < matrix.length; ++r) {
            if (matrix[r][0] == 0) {
                col0 = true;
                break;
            }
        }
        for (int c = 0; c < matrix[0].length; ++c) {
            if (matrix[0][c] == 0) {
                row0 = true;
                break;
            }
        }

        for (int r = 1; r < matrix.length; ++r) {
            for (int c = 1; c < matrix[r].length; ++c) {
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0;
                    matrix[0][c] = 0;
                }
            }
        }
        for (int r = 1; r < matrix.length; ++r) {
            if (matrix[r][0] == 0) {
                for (int c = 1; c < matrix[r].length; ++c)
                    matrix[r][c] = 0;
            }
        }
        for (int c = 1; c < matrix[0].length; ++c) {
            if (matrix[0][c] == 0) {
                for (int r = 1; r < matrix.length; ++r)
                    matrix[r][c] = 0;
            }
        }

        if (row0) {
            for (int c = 0; c < matrix[0].length; ++c)
                matrix[0][c] = 0;
        }
        if (col0) {
            for (int r = 0; r < matrix.length; ++r)
                matrix[r][0] = 0;
        }
    }
}
