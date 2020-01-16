package com.rainz;

/*
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 */

public class GameofLife {
    public static void test(String args[]) {
        int[][] input1 = {
                        {0,1,0},
                        {0,0,1},
                        {1,1,1},
                        {0,0,0}
                        };
        gameOfLife(input1);
        Main.printArray2D(input1);
    }


    public static void gameOfLife(int[][] board) {
        int R = board.length;
        if (R == 0)
            return;
        int C = board[0].length;
        final int flipFlag = 0x80;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                // Count neighbors
                int nbCount = 0;
                for (int i = -1; i <= 1; ++i) {
                    for (int j = -1; j <= 1; ++j) {
                        if (i == 0 && j == 0)
                            continue; // don't count self
                        int newR = r + i, newC = c + j;
                        if (newR < 0 || newR >= R || newC < 0 || newC >= C)
                            continue; // boundary check
                        if (board[newR][newC] % 2 > 0)
                            ++nbCount;
                    }
                }
                if (board[r][c] == 1) {
                    if (nbCount < 2 || nbCount > 3)
                        board[r][c] |= flipFlag; // need to flip
                } else if (board[r][c] == 0 && nbCount == 3) {
                    board[r][c] |= flipFlag; // need to flip
                }
            }
        }

        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if ((board[r][c] & flipFlag) != 0)
                    board[r][c] = 1 - board[r][c]%2;
            }
        }
    }
}
