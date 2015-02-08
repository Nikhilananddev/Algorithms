package com.rainz;

/**
 * Created by Yu on 2/8/2015.
 */
public class SpiralMatrixII {
    public static void test(String args[]) {
        int n = 4;
        int[][] answer = generateMatrix(n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.printf("%d ", answer[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] answer = new int[n][n];
        int dir = 0;
        final int[][] dirs = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        int row = 0, col = 0;
        for (int i = 1; i <= n*n; ++i) {
            answer[row][col] = i;
            int nextRow = row + dirs[dir][0];
            int nextCol = col +dirs[dir][1];
            if (nextRow >= n || nextRow < 0 || nextCol >= n || nextCol < 0 || answer[nextRow][nextCol] > 0) {
                dir = (dir + 1) % 4;
                row += dirs[dir][0];
                col += dirs[dir][1];
            } else {
                row = nextRow;
                col = nextCol;
            }
        }
        return answer;
    }
}
