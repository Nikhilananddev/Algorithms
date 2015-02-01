package com.rainz;

/**
 * Created by Yu on 2/1/2015.
 */
public class ValidSudoku {
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
        System.out.println(isValidSudoku(test));
    }

    protected static boolean validate(char[][] board, int row, int col, int w, int h) {
        boolean[] found = new boolean[10];
        for (int r = row; r < row+w; ++r) {
            for (int c = col; c < col+h; ++c) {
                char ch = board[r][c];
                if (ch == '.')
                    continue;
                int digit = ch - '0';
                if (found[digit])
                    return false;
                found[digit] = true;
            }
        }
        return true;
    }

    public static boolean isValidSudoku(char[][] board) {
        int dimension = board.length;
        // Validate rows
        for (int row = 0; row < dimension; ++row) {
            if (!validate(board, row, 0, 1, dimension))
                return false;
        }
        // Validate cols
        for (int col = 0; col < dimension; ++col) {
            if (!validate(board, 0, col, dimension, 1))
                return false;
        }
        // Validate each 3x3
        for (int row = 0; row < dimension; row += 3) {
            for (int col = 0; col < dimension; col +=3) {
                if (!validate(board, row, col, 3, 3))
                    return false;
            }
        }
        return true;
    }
}
