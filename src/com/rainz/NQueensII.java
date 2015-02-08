package com.rainz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu on 2/8/2015.
 */
public class NQueensII {
    public static void test(String args[]) {
        System.out.println(totalNQueens(4));
    }

    protected static boolean check(int[] positions, int row) {
        for (int i = 0; i < row; ++i) {
            if (positions[i] == positions[row])
                return false;
            if (Math.abs(positions[row] - positions[i]) == row - i)
                return false;
        }
        return true;
    }

    protected static int helper(int[] positions, int row) {
        int n = positions.length;
        if (row >= n) {
            return 1;
        }
        int answer = 0;
        for (positions[row] = 0; positions[row] < n; ++positions[row]) {
            if (check(positions, row))
                answer += helper(positions, row+1);
        }
        return answer;
    }

    public static int totalNQueens(int n) {
        int[] positions = new int[n];
        return helper(positions, 0);
    }
}
