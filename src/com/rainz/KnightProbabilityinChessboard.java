package com.rainz;

import java.util.Arrays;

/*
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 * A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.
 */
public class KnightProbabilityinChessboard {
    public static void test(String args[]) {
        System.out.println(knightProbability(3,2,0,0));
        System.out.println(knightProbability(10,13,5,3));
    }
    public static double knightProbabilityRecursive(int N, int K, int r, int c) {
        final int[][] dirs = new int[][] {{2,1}, {2, -1}, {-2,1}, {-2, -1},  {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};
        if (r < 0 || r >= N || c < 0 || c >= N)
            return 0;
        if (K == 0)
            return 1;
        double p = 0;
        for (int[] dir : dirs) {
            int rNew = r + dir[0];
            int cNew = c + dir[1];
            if (rNew >= 0 && rNew < N && cNew >= 0 && cNew < N) {
                p += knightProbabilityRecursive(N, K-1, rNew, cNew);
            }
        }
        return p/8;
    }

    public static double knightProbability(int N, int K, int r, int c) {
        final int[][] dirs = new int[][] {{2,1}, {2, -1}, {-2,1}, {-2, -1},  {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};
        double[][] prob = new double[N][N];
        for (double[] row: prob)
            Arrays.fill(row, 1);
        for (int step = 0; step < K; ++step) {
            double[][] newProb = new double[N][N];
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    double p = 0;
                    for (int[] dir : dirs) {
                        int rNew = i + dir[0];
                        int cNew = j + dir[1];
                        if (rNew >= 0 && rNew < N && cNew >= 0 && cNew < N) {
                            p += prob[rNew][cNew];
                        }
                    }
                    newProb[i][j] = p / 8.0;
                }
            }
            prob = newProb;
        }
        return prob[r][c];
    }
}
