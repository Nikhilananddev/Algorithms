package com.rainz;

/*
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 * You may assume the following rules:
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves is allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 */
public class DesignTicTacToe {
    public static void test(String args[]) {
        DesignTicTacToe toe = new DesignTicTacToe(3);
        System.out.println(toe.move(0, 0, 1)); // -> Returns 0 (no one wins)
        System.out.println(toe.move(0, 2, 2)); // -> Returns 0 (no one wins)
        System.out.println(toe.move(2, 2, 1)); // -> Returns 0 (no one wins)
        System.out.println(toe.move(1, 1, 2)); // -> Returns 0 (no one wins)
        System.out.println(toe.move(2, 0, 1)); // -> Returns 0 (no one wins)
        System.out.println(toe.move(1, 0, 2)); // -> Returns 0 (no one wins)
        System.out.println(toe.move(2, 1, 1)); // -> Returns 1 (player 1 wins)
    }
    private int N;
    private int winner;
    private int[][] board;
    public DesignTicTacToe(int n) {
        N = n;
        board = new int[n][n];
        winner = 0;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if (winner != 0)
            return winner;
        board[row][col] = player;
        // Check row
        boolean same = true;
        for (int r = 0; r < N; ++r) {
            if (board[r][col] != player) {
                same = false;
                break;
            }
        }
        if (same) {
            winner = player;
            return winner;
        }
        // Check cols
        same = true;
        for (int c = 0; c < N; ++c) {
            if (board[row][c] != player) {
                same = false;
                break;
            }
        }
        if (same) {
            winner = player;
            return winner;
        }
        // Check diagonal 1
        if (row == col) {
            same = true;
            for (int i = 0; i < N; ++i) {
                if (board[i][i] != player) {
                    same = false;
                    break;
                }
            }
        }
        if (same) {
            winner = player;
            return winner;
        }
        // Check diagonal 2
        if (row + col == N - 1) {
            same = true;
            for (int i = 0; i < N; ++i) {
                if (board[i][N - 1 - i] != player) {
                    same = false;
                    break;
                }
            }
        }
        if (same) {
            winner = player;
            return winner;
        }
        return 0;
    }
}
