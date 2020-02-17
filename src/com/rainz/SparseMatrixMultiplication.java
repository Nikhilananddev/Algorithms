package com.rainz;

/*
 * Given two sparse matrices A and B, return the result of AB.
 * You may assume that A's column number is equal to B's row number.
 */
public class SparseMatrixMultiplication {
    public static void test(String args[]) {
        int[][] A1 = {
                        { 1, 0, 0},
                        {-1, 0, 3}
                     };

        int[][] B1 = {
                        { 7, 0, 0 },
                        { 0, 0, 0 },
                        { 0, 0, 1 }
                     };
        Main.printArray2D(multiply(A1, B1));
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int I = A.length;
        int K = A[0].length;
        int J = B[0].length;
        int[][] ans = new int[I][J];
        for (int i = 0; i < I; ++i) {
            for (int k = 0; k < K; ++k) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < J; ++j) {
                        // A[i][k] contributes to the entire row of ans[i];
                        if (B[k][j] != 0)
                            ans[i][j] += A[i][k]*B[k][j];
                    }
                }
            }
        }
        return ans;
    }
}
