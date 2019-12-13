package com.rainz;

public class CountServersThatCommunicate {
    public static void test(String args[]) {
        int[][] input1 = {{1,0},{0,1}};
        int[][] input2 = {{1,0},{1,1}};
        int[][] input3 = {{1,1,0,0},{0,0,1,0},{0,0,1,0},{0,0,0,1}};
        System.out.println(countServers(input1));
        System.out.println(countServers(input2));
        System.out.println(countServers(input3));
    }

    public static int countServers(int[][] grid) {
        if (grid.length == 0)
            return 0;
        int numRows = grid.length;
        int numCols = grid[0].length;
        boolean[][] notAlone = new boolean[numRows][numCols];
        int result = 0;
        for (int r = 0; r < numRows; ++r) {
            int[] row = grid[r];
            for (int c = 0; c < numCols; ++c) {
                if (grid[r][c] != 1)
                    continue;;
                if (notAlone[r][c]) {
                    ++result;
                    continue;
                }
                // Check row
                for (int idx = 0; idx < numCols; ++idx) {
                    if (idx != c && grid[r][idx] == 1) {
                        notAlone[r][c] = true;
                        notAlone[r][idx] = true;
                    }
                }
                // Check column
                for (int idx = 0; idx < numRows; ++idx) {
                    if (idx != r && grid[idx][c] == 1) {
                        notAlone[r][c] = true;
                        notAlone[idx][c] = true;
                    }
                }
                if (notAlone[r][c])
                    ++result;
            }
        }
        return result;
    }
}
