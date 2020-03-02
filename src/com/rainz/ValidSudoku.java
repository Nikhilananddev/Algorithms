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
        
        char[][] test2 = {{'7','4','8', '6','3','5', '2','9','1'},
                          {'1','9','3', '4','7','2', '6','5','8'},
                          {'6','5','2', '8','1','9', '4','3','7'},
                          {'2','6','5', '9','4','8', '7','1','3'},
                          {'8','7','9', '1','2','3', '5','4','6'},
                          {'3','1','4', '7','5','6', '8','2','9'},
                          {'9','2','7', '3','6','4', '1','8','5'},
                          {'5','3','6', '2','8','1', '9','7','4'},
                          {'4','8','1', '5','9','7', '3','6','2'}};
        System.out.println(isValidSudoku(test2));
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
