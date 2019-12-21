package com.rainz;

/*
 * We have a two dimensional matrix A where each value is 0 or 1.
 * A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.
 * After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
 * Return the highest possible score.
 */

/*
 * Note: there are 2 ways to make highest column 1's: just flip the 0-rows, or flip highest column then flip 0-rows.
 * The end result of these two are the same. So no need to try both.
 */

public class ScoreAfterFlippingMatrix {
    public static void test(String args[]) {
        int input[][] = {{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        System.out.println(matrixScore(input));
    }

    public static int matrixScore(int[][] A) {
        int rows = A.length;
        if (rows == 0)
            return 0;
        int cols = A[0].length;
        boolean[] rowFlip = new boolean[rows];
        for (int i = 0; i < rows; ++i)
            rowFlip[i] = (A[i][0] == 0);

        int result = 0;
        int shift = 0;
        for (; shift < cols-1; ++shift) {
            int col = cols - shift - 1;
            int count0 = 0;
            int count1 = 0;
            for (int row = 0; row < rows; ++row) {
                int val = A[row][col];
                if (rowFlip[row])
                    val = 1 - val;
                if (val == 0)
                    ++count0;
                else
                    ++count1;
            }
            int count = count0 > count1 ? count0 : count1;
            result += (count << shift);
        }
        // The most significant column contains all 1's
        result += rows * (1 << shift);
        return result;
    }
}
