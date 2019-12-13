package com.rainz;

public class CellsWithOddValuesInAMatrix {
    public static void test(String args[]) {
        int[][] input = { {0,1},{1,1}};
        System.out.println(oddCells(2, 3, input));
        int[][] input2 = { {1,1},{0,0}};
        System.out.println(oddCells(2, 2, input2));
    }

    public static int oddCells(int n, int m, int[][] indices) {
        // View each row & column increments as two operations
        // Incrementing odd # times is equivalent to incrementing once
        // Incrementing even # times is equivalent to not incrementing

        // First, determine odd row and column counts
        boolean[] rows = new boolean[n];
        boolean[] cols = new boolean[m];

        for (int[] idx: indices) {
            int r = idx[0];
            int c = idx[1];
            rows[r] = !rows[r];
            cols[c] = !cols[c];
        }
        int oddRows = 0;
        for (boolean b: rows) {
            if (b)
                ++oddRows;
        }
        int oddCols = 0;
        for (boolean b: cols) {
            if (b)
                ++oddCols;
        }

        // Now imagine applying increment just once to these rows & columns each
        // Result = #cell - #overlaps,
        // while #cell = sum of cells in these rols & cols - #overlaps
        // So result = sum of cells in these rols & cols - 2*#overlaps
        return oddRows*m + oddCols*n - 2*oddRows*oddCols;
    }
}
