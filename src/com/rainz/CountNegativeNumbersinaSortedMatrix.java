package com.rainz;

/*
 * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
 * Return the number of negative numbers in grid.
 */
public class CountNegativeNumbersinaSortedMatrix {
    public static void test(String args[]) {
        int[][] grid1 = {{4,3,2,-1},{3,2,1,-1},{1,1,-1,-2},{-1,-1,-2,-3}};
        System.out.println(countNegatives(grid1));
        int[][] grid2 = {{3,2},{1,0}};
        System.out.println(countNegatives(grid2));
        int[][] grid3 = {{1,-1},{-1,-1}};
        System.out.println(countNegatives(grid3));
        int[][] grid4 = {{-1}};
        System.out.println(countNegatives(grid4));
    }

    // Similar to find element in a sorted matrix.
    // Start from upper right, every time you eliminate either 1 row or 1 col.
    public static int countNegatives(int[][] grid) {
        int R = grid.length;
        if (R == 0)
            return 0;
        int C = grid[0].length;
        int ans = 0;
        int r = 0, c = C-1;
        while (r < R && c >= 0) {
            int n = grid[r][c];
            if (n >= 0)
                ++r;
            else {
                --c;
                ans += R - r;
            }
        }
        return ans;
    }
}
