package com.rainz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu on 2/7/2015.
 */
public class NQueens {
    public static void test(String args[]) {
        List<String[]> answer = solveNQueens(4);
        for (String[] strs: answer) {
            for (String s: strs) {
                System.out.println(s);
            }
            System.out.println();
        }
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

    protected static void helper(int[] positions, int row, List<String[]> answer) {
        int n = positions.length;
        if (row >= n) {
            String[] sol = new String[n];
            for (int i = 0; i < positions.length; ++i) {
                StringBuilder bldr = new StringBuilder();
                for (int pos = 0; pos < n; ++pos)
                    bldr.append(pos == positions[i] ? 'Q' : '.');
                sol[i] = bldr.toString();
            }
            answer.add(sol);
            return;
        }
        for (positions[row] = 0; positions[row] < n; ++positions[row]) {
            if (check(positions, row))
                helper(positions, row+1, answer);
        }
    }

    public static List<String[]> solveNQueens(int n) {
        List<String[]> answer = new ArrayList<String[]>();
        int[] positions = new int[n];
        helper(positions, 0, answer);
        return answer;
    }
}
