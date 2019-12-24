package com.rainz;

/*
 * Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
 */

public class MaximumSideLengthofaSquarewithSumLessthanorEqualtoThreshold {
    public static void test(String args[]) {
        int[][] mat1 = {{1,1,3,2,4,3,2},{1,1,3,2,4,3,2},{1,1,3,2,4,3,2}};
        System.out.println(maxSideLength(mat1, 4));
        int[][] mat2 = {{2,2,2,2,2},{2,2,2,2,2},{2,2,2,2,2},{2,2,2,2,2},{2,2,2,2,2}};
        System.out.println(maxSideLength(mat2, 1));
        int[][] mat3 = {{1,1,1,1},{1,0,0,0},{1,0,0,0},{1,0,0,0}};
        System.out.println(maxSideLength(mat3, 6));
        int[][] mat4 = {{18,70},{61,1},{25,85},{14,40},{11,96},{97,96},{63,45}};
        System.out.println(maxSideLength(mat4, 40184));
    }

    public static int maxSideLength(int[][] mat, int threshold) {
        int rows = mat.length;
        if (rows <= 0)
            return 0;
        int cols = mat[0].length;
        int[][] sums = new int[rows][cols];
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                int prevRSum = r > 0 ? sums[r-1][c] : 0;
                int prevCSum = c > 0 ? sums[r][c-1] : 0;
                int prevRCSum = (r > 0 && c > 0) ? sums[r-1][c-1] : 0;
                sums[r][c] = mat[r][c] + prevRSum + prevCSum - prevRCSum ;
            }
        }

        int ans = 0;
        final int MAX_SIDE = rows > cols ? rows : cols;
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                int lower = 1;
                int upper = MAX_SIDE;
                int len = 0;
                while (lower <= upper) {
                    int mid = (lower+upper)/2;
                    // row and col for lower right corner
                    int r2 = r + mid - 1;
                    int c2 = c + mid - 1;
                    if (r2 >= rows || c2 >= cols) {
                        upper = mid - 1;
                        continue;
                    }
                    int upperSum = r > 0 ? sums[r-1][c2] : 0;
                    int leftSum = c > 0 ? sums[r2][c-1] : 0;
                    int ulSum = (r > 0 && c > 0) ? sums[r-1][c-1] : 0;
                    int sum = sums[r2][c2] - upperSum - leftSum + ulSum;
                    if (sum <= threshold) {
                        len = mid;
                        lower = mid + 1;
                    } else {
                        upper = mid - 1;
                    }
                }
                if (len > ans)
                    ans = len;
            }
        }

        return ans;
    }
}
