package com.rainz;

/*
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQuery2DMutable {
    public static void test(String args[]) {
        int[][] input1 = {
                          {3, 0, 1, 4, 2},
                          {5, 6, 3, 2, 1},
                          {1, 2, 0, 1, 5},
                          {4, 1, 0, 1, 7},
                          {1, 0, 3, 0, 5}
        };
        RangeSumQuery2DMutable q = new RangeSumQuery2DMutable(input1);
        System.out.println(q.sumRegion(2,1, 4, 3)); // 8
        q.update(3, 2, 2);
        System.out.println(q.sumRegion(2,1, 4, 3)); // 10
    }

    /*
     * This problem can also be solved with Binary Indexed Tree (Fenwick Tree)
     * Below is the column-sum solution.
     */
    private int[][] mat;
    private int[][] colSum;
    public RangeSumQuery2DMutable(int[][] matrix) {
        mat = matrix;
        int R = mat.length;
        if (R == 0)
            return;
        int C = mat[0].length;
        if (C == 0)
            return;
        colSum = new int[R+1][C];
        for (int i = 1; i < R+1; ++i) {
            for (int j = 0; j < C; ++j) {
                colSum[i][j] = colSum[i - 1][j] + mat[i - 1][j];
            }
        }
    }

    public void update(int row, int col, int val) {
        int diff = val - mat[row][col];
        for (int i = row + 1; i < colSum.length; ++i) {
            colSum[i][col] += diff;
        }
        mat[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int j = col1; j <= col2; ++j) {
            res += colSum[row2 + 1][j] - colSum[row1][j];
        }
        return res;
    }
}
