package com.rainz;

/**
 * Created by Yu on 8/21/2016.
 */
public class DungeonGame {
    public static void test(String args[]) {
        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        System.out.println(calculateMinimumHP(dungeon));
    }

    public static int calculateMinimumHP(int[][] dungeon) {
        int numRows = dungeon.length;
        if (numRows == 0) {
            return 0;
        }
        int numCols = dungeon[0].length;
        int[][] dp = new int[numRows][numCols];

        for (int row = numRows - 1; row >= 0; --row) {
            for (int col = numCols - 1; col >= 0; -- col) {
                if (row == numRows - 1 && col == numCols - 1) {
                    dp[row][col] = -dungeon[row][col] + 1;
                } else {
                    int currCell = dungeon[row][col];
                    int rightHP = col < numCols - 1 ? dp[row][col + 1] : Integer.MAX_VALUE;
                    int downHP = row < numRows - 1 ? dp[row + 1][col] : Integer.MAX_VALUE;
                    dp[row][col] = Math.min(rightHP, downHP) - currCell;
                }
                if (dp[row][col] < 1) {
                    dp[row][col] = 1;
                }
            }
        }

        return dp[0][0];
    }
}
