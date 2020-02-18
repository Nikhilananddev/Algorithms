package com.rainz;

/*
 * Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 */
public class MatrixBlockSum {
    public static void test(String args[]) {
//        int[][] input1 = {{1,2,3},{4,5,6},{7,8,9}};
//        Main.printArray2D(matrixBlockSum(input1, 1));
//        int[][] input2 = {{1,2,3},{4,5,6},{7,8,9}};
//        Main.printArray2D(matrixBlockSum(input2, 2));
        int[][] input3 = {{72},{69},{55},{50},{80}};
        Main.printArray2D(matrixBlockSum(input3, 2));
    }

    /*
     * For each row, first compute prefix sum.
     * Then, each sum of [c-K, c+k] contributes to [r-K, r+K] rows.
     */
    public static int[][] matrixBlockSum(int[][] mat, int K) {
        int R = mat.length;
        int C = mat[0].length;
        int[] pSum = new int[C+1];
        int[][] ans = new int[R][C];
        for (int r = 0; r < R; ++r) {
            pSum[0] = 0;
            for (int c = 0; c < C; ++c)
                pSum[c+1] = pSum[c] + mat[r][c];
            for (int c = 0; c < C; ++c) {
                int startC = Math.max(0, c-K);
                int endC = Math.min(C-1, c+K);
                int sumK = pSum[endC+1] - pSum[startC];
                int startR = Math.max(0, r-K);
                int endR = Math.min(R-1, r+K);
                for (int rr = startR; rr <= endR; ++rr)
                    ans[rr][c] += sumK;
            }
        }
        return ans;
    }
}
