package com.rainz;

/*
 * A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.
 * The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.
 * Here are the rules of Tic-Tac-Toe:
 * Players take turns placing characters into empty squares (" ").
 * The first player always places "X" characters, while the second player always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never filled ones.
 * The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 */
public class ValidTicTacToeState {
    public static void test(String args[]) {
        String[] input1 = {"O  ", "   ", "   "};
        System.out.println(validTicTacToe(input1));
        String[] input2 = {"XOX", " X ", "   "};
        System.out.println(validTicTacToe(input2));
        String[] input3 = {"XXX", "   ", "OOO"};
        System.out.println(validTicTacToe(input3));
        String[] input4 = {"XOX", "O O", "XOX"};
        System.out.println(validTicTacToe(input4));
        String[] input5 = {"XXX", "XOO", "XOO"};
        System.out.println(validTicTacToe(input5));
        String[] input6 = {"XXX","XOO","OO "};
        System.out.println(validTicTacToe(input6));
    }
    /*
     * There could be at most one more Xs than Os.
     * There can't be two or more 3-of-a-kind unless these two are on the same side and intersect with each other
     */
    public static boolean validTicTacToe(String[] board) {
        int Xs = 0, Os = 0;
        char prev3 = '\0';
        char prev3Type = '\0'; // 'R': row, 'C': col
        for (int i = 0; i < 3; ++i) {
            String row = board[i];
            // Check multiple same-3 in rows
            char c0 = row.charAt(0);
            if (c0 != ' ' && c0 == row.charAt(1) && c0 == row.charAt(2)) {
                // same 3 in this row
                if (prev3 != '\0' && (prev3 != c0 || prev3Type == 'R'))
                    return false;
                prev3 = c0;
                prev3Type = 'R';
            }
            // Compute total counts
            for (int j = 0; j < 3; ++j) {
                char c = row.charAt(j);
                if (c == 'X')
                    ++Xs;
                else if (c == 'O')
                    ++Os;
                else if (c != ' ')
                    return false;
            }
        }
        if (Xs < Os || Xs > Os + 1)
            return false;
        // Check multiple same-3 in cols
        for (int i = 0; i < 3; ++i) {
            char c0 = board[0].charAt(i);
            if (c0 != ' ' && c0 == board[1].charAt(i) && c0 == board[2].charAt(i)) {
                // same 3 in this col
                if (prev3 != '\0' && (prev3 != c0 || prev3Type == 'C'))
                    return false;
                prev3 = c0;
                prev3Type = 'C';
            }
        }
        // Check same-3 in left diagonal
        char c0 = board[0].charAt(0);
        if (c0 != ' ' && c0 == board[1].charAt(1) && c0 == board[2].charAt(2)) {
            if (prev3 != '\0' && prev3 != c0)
                return false;
            prev3 = c0;
        }
        // Check same-3 in right diagonal
        c0 = board[0].charAt(2);
        if (c0 != ' ' && c0 == board[1].charAt(1) && c0 == board[2].charAt(0)) {
            if (prev3 != '\0' && prev3 != c0)
                return false;
            prev3 = c0;
        }
        // If 'X' finishes the game, no more Os. Xs should be Os+1
        // If 'O' finishes the game, no more Xs. Xs should equal Os
        if (prev3 == 'X')
            return Xs == Os + 1;
        else if (prev3 == 'O')
            return Xs == Os;
        return true;
    }

}
