package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountSquareSubmatricesWithAllOnes {
    public static void test(String args[]) {
        int[][] input = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        System.out.println(countSquareSubmatricesWithAllOnes(input));
    }

    public static int countSquareSubmatricesWithAllOnes(int[][] matrix) {
        int result = 0;
        int rows = matrix.length;
        if (rows == 0)
            return result;
        int cols = matrix[0].length;
        int maxSqSz = rows >= cols ? rows : cols;

        List<int[]> prevSquares = new ArrayList<>();
        // First, add squares with size 1
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (matrix[i][j] == 1) {
                    int[] sq = {i, j};
                    prevSquares.add(sq);
                    ++result;
                }
            }
        }

        // Now count squares with size 2 to max size
        for (int sz = 2; sz <= maxSqSz; ++sz) {
            List<int[]> squares = new ArrayList<>();
            for (int[] sq: prevSquares) {
                int bottom = sq[0] + sz - 1;
                int right = sq[1] + sz - 1;
                if (bottom >= rows || right >= cols)
                    continue;
                boolean allOne = true;
                // Check bottom row
                for (int c = 0; c < sz; ++c) {
                    if (matrix[bottom][c+sq[1]] != 1) {
                        allOne = false;
                        break;
                    }
                }
                if (allOne) {
                    // Check right column
                    for (int r = 0; r < sz-1; ++r) {
                        if (matrix[r+sq[0]][right] != 1) {
                            allOne = false;
                            break;
                        }
                    }
                }
                if (allOne) {
                    int[] newSq = sq.clone();
                    squares.add(newSq);
                    ++result;
                }
            }
            prevSquares = squares;
        }

        return result;
    }
}
