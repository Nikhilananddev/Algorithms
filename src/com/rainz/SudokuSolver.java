package com.rainz;

/**
 * Created by Yu on 2/1/2015.
 */
public class SudokuSolver {
    public static void test(String args[]) {
        char[][] test = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        };
        solveSudoku(test);
        for (int row = 0; row < test.length; ++row) {
            for (int col = 0; col < test[row].length; ++col) {
                System.out.print("" + test[row][col] + ' ');
            }
            System.out.println();
        }
    }

    protected static int dimension;

    protected static boolean helper(char[][] board, int row, int col) {
        if (row == dimension)
            return true; // done
        int nextRow, nextCol;
        if (col == dimension - 1) {
            nextRow = row + 1;
            nextCol = 0;
        } else {
            nextRow = row;
            nextCol = col + 1;
        }
        char curr = board[row][col];
        if (curr != '.') {
            // curr cell is already filled, just go to next cell
            return helper(board, nextRow, nextCol);
        }
        boolean[] seen = new boolean[9];
        int rowGrid = row - row % 3, colGrid = col - col % 3;
        for (int i = 0; i < dimension; ++i) {
            // Check this row
            if (i != row) {
                char c = board[i][col];
                if (c != '.')
                    seen[c - '1'] = true;
            }
            // Check this column
            if (i != col) {
                char c = board[row][i];
                if (c != '.')
                    seen[c - '1'] = true;
            }
            // Check each of 3x3 cells
            int rGrid = rowGrid + i / 3, cGrid = colGrid + i % 3;
            if (rGrid != row && cGrid != col) {
                char c = board[rGrid][cGrid];
                if (c != '.')
                    seen[c - '1'] = true;
            }
        }
        for (int i = 0; i < seen.length; ++i) {
            // Try each number not in row, col, or 3x3 grid
            if (seen[i])
                continue;
            board[row][col] = (char)('1' + i);
            if (helper(board, nextRow, nextCol))
                return true;
        }
        board[row][col] = '.';
        return false;
    }

    public static void solveSudoku(char[][] board) {
        dimension = board.length;
        helper(board, 0, 0);
    }
}
