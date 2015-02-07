package com.rainz;

/**
 * Created by Yu on 2/7/2015.
 */
public class RotateImage {
    public static void test(String args[]) {
        int[][] test = {
                {11, 12, 13},
                {21, 22, 23},
                {31, 32, 33}
        };
        rotate(test);
        for (int[] row: test) {
            for (int val: row)
                System.out.print("" + val + " ");
            System.out.println();
        }
    }
    public static void rotate(int[][] matrix) {
        int ring = 0;
        while (ring < (matrix.length+1)/2 ) {
            int len = matrix.length - 2*ring - 1;
            for (int i = 0; i < len; ++i) {
                int maxIdx = matrix.length - 1 - ring;
                int sav = matrix[ring][ring+i];
                matrix[ring][ring+i] = matrix[maxIdx-i][ring];
                matrix[maxIdx-i][ring] = matrix[maxIdx][maxIdx-i];
                matrix[maxIdx][maxIdx-i] = matrix[ring+i][maxIdx];
                matrix[ring+i][maxIdx] = sav;
            }
            ++ring;
        }
    }
}
