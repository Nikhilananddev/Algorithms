package com.rainz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu on 2/8/2015.
 */
public class SpiralMatrix {
    public static void test(String args[]) {
        int[][] test = {
                {1 ,2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11,12,13,14,15},
        };
        System.out.println(spiralOrder(test));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<Integer>();
        final int ROWS = matrix.length;
        if (ROWS == 0)
            return answer;
        final int COLS = matrix[0].length;
        final int[][] dirs = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        final int[] sidesLen = {COLS, ROWS-1, COLS-1, ROWS-2};
        int row = 0, col = 0;
        int dir = 0;
        int runLen = 0, runMaxLen = sidesLen[0];
        int ring = 0;
        for (int i = 1; i <= COLS*ROWS; ++i) {
            answer.add(matrix[row][col]);
            ++runLen;
            if (runLen == runMaxLen) {
                ++dir; // change dir
                runLen = 0;
                if (dir > 3) {
                    // One ring is complete
                    ++ring;
                    dir = 0;
                }
                runMaxLen = sidesLen[dir] - 2*ring;
            }
            row += dirs[dir][0];
            col += dirs[dir][1];
        }
        return answer;
    }
}
