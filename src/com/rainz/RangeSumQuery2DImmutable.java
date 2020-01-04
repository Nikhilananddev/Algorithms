package com.rainz;

public class RangeSumQuery2DImmutable {
    public static void test(String args[]) {
        int[][] matrix = {
                          {3, 0, 1, 4, 2},
                          {5, 6, 3, 2, 1},
                          {1, 2, 0, 1, 5},
                          {4, 1, 0, 1, 7},
                          {1, 0, 3, 0, 5}
                         };
        RangeSumQuery2DImmutable rangeSummary = new RangeSumQuery2DImmutable(matrix);
        System.out.println(rangeSummary.sumRegion(2, 1, 4, 3)); // -> 8
        System.out.println(rangeSummary.sumRegion(1, 1, 2, 2)); // -> 11
        System.out.println(rangeSummary.sumRegion(1, 2, 2, 4)); // -> 12
    }

    private int[][] sums;

    public RangeSumQuery2DImmutable(int[][] matrix) {
        int R = matrix.length;
        int C = R > 0 ? matrix[0].length : 0;
        sums = new int [R][C];
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                int u = r > 0 ? sums[r-1][c] : 0;
                int l = c > 0 ? sums[r][c-1] : 0;
                int ul = r > 0 && c > 0 ? sums[r-1][c-1] : 0;
                sums[r][c] = matrix[r][c] + u + l - ul;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int u = row1 > 0 ? sums[row1-1][col2] : 0;
        int l = col1 > 0 ? sums[row2][col1-1] : 0;
        int ul = row1 > 0 && col1 > 0 ? sums[row1-1][col1-1] : 0;
        return sums[row2][col2] - u - l + ul;
    }
}
