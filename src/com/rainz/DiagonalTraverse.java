package com.rainz;

/*
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order.
 */

public class DiagonalTraverse {
    public static void test(String args[]) {
        int[][] input = {
                         { 1, 2, 3 },
                         { 4, 5, 6 },
                         { 7, 8, 9 }
                        };
        Main.printArray(findDiagonalOrder(input));
    }
    public static int[] findDiagonalOrder(int[][] matrix) {
        int R = matrix.length;
        if (R == 0)
            return new int[0];
        int C = matrix[0].length;

        int dr = -1, dc = 1;
        int totalCount = R*C;
        int[] ans = new int[totalCount];
        int idx = 0;
        int r = 0, c = 0;
        while (idx < totalCount) {
            ans[idx++] = matrix[r][c];
            int newR = r + dr;
            int newC = c + dc;
            if (newR < 0 || newR >= R || newC < 0 || newC >= C) {
                dr = -dr;
                dc = -dc;
            }
            if (newR < 0) {
                if (newC >= C) {
                    // Upper right corner
                    newR = 1;
                    newC = C - 1;
                } else {
                    // Top
                    newR = 0;
                }
            } else if (newR >= R) {
                if (newC < 0) {
                    // Lower right corner
                    newR = R - 1;
                    newC = 1;
                } else {
                    // Bottom
                    newR = R - 1;
                    newC += 2;
                }
            } else if (newC < 0) {
                // Left
                newC = 0;
            } else if (newC >= C) {
                // Right
                newR += 2;
                newC = C - 1;
            }

            r = newR;
            c = newC;
        }

        return ans;
    }
}
