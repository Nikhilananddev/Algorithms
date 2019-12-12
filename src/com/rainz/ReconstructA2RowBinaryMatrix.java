package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class ReconstructA2RowBinaryMatrix {
    public static void test(String args[]) {
        int upper = 2, lower = 1;
        int[] colsum = {1,1,1};
        var result = solve(upper, lower, colsum);
        result.forEach( value -> {value.forEach( v -> System.out.print( ""+v+",")); System.out.println(); });
        System.out.println();

        upper = 2;
        lower = 3;
        int[] colsum2 = {2,2,1,1};
        result = solve(upper, lower, colsum2);
        result.forEach( value -> {value.forEach( v -> System.out.print( ""+v+",")); System.out.println(); });
        System.out.println();

        upper = 5;
        lower = 5;
        int[] colsum3 = {2,1,2,0,1,0,1,2,0,1};
        result = solve(upper, lower, colsum3);
        result.forEach( value -> {value.forEach( v -> System.out.print( ""+v+",")); System.out.println(); });
        System.out.println();

        upper = 3;
        lower = 3;
        int[] colsum4 = {0,1,1,0,1,0,2,1};
        result = solve(upper, lower, colsum4);
        result.forEach( value -> {value.forEach( v -> System.out.print( ""+v+",")); System.out.println(); });
        System.out.println();
    }

    private static boolean helper(int upper, int lower, int[] colsum, List<Integer> r1, List<Integer> r2, int start) {
        if (start >= colsum.length) {
            return (upper == 0 && lower == 0);
        }
        if (colsum[start] == 0) {
            r1.add(0);
            r2.add(0);
            if (helper(upper, lower, colsum, r1, r2, start+1))
                return true;
            r1.remove(r1.size() - 1);
            r2.remove(r2.size() - 1);
        } else if (colsum[start] == 2) {
            if (upper <= 0 || lower <= 0)
                return false;
            r1.add(1);
            r2.add(1);
            if (helper(upper-1, lower-1, colsum, r1, r2, start+1))
                return true;
            r1.remove(r1.size() - 1);
            r2.remove(r2.size() - 1);
        } else {
            if (upper > 0) {
                // Try 1, 0
                r1.add(1);
                r2.add(0);
                if (helper(upper-1, lower, colsum, r1, r2, start+1))
                    return true;
                r1.remove(r1.size() - 1);
                r2.remove(r2.size() - 1);
            }
            if (lower > 0) {
                // Try 0, 1
                r1.add(0);
                r2.add(1);
                if (helper(upper, lower-1, colsum, r1, r2, start+1))
                    return true;
                r1.remove(r1.size() - 1);
                r2.remove(r2.size() - 1);
            }
        }
        return false;
    }

    public static List<List<Integer>> solve_recursive(int upper, int lower, int[] colsum) {
        int len = colsum.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> r1 = new ArrayList<Integer>(len);
        List<Integer> r2 = new ArrayList<Integer>(len);

        boolean found = helper(upper, lower, colsum, r1, r2,0);
        if (found) {
            result.add(r1);
            result.add(r2);
        }
        return result;
    }

    // Greedy algorithm.
    public static List<List<Integer>> solve(int upper, int lower, int[] colsum) {
        int len = colsum.length;
        int[][] rows = new int[2][colsum.length];
        for (int i = 0; i < colsum.length; ++i) {
            if (colsum[i] == 2) {
                rows[0][i] = 1;
                rows[1][i] = 1;
                --upper;
                --lower;
            } else if (colsum[i] == 1) {
                if (upper >= lower) {
                    rows[0][i] = 1;
                    --upper;
                } else {
                    rows[1][i] = 1;
                    --lower;
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        if (upper == 0 & lower == 0) {
            List<Integer> r1 = new ArrayList<>();
            List<Integer> r2 = new ArrayList<>();
            for (int i = 0; i < colsum.length; ++i) {
                r1.add(rows[0][i]);
                r2.add(rows[1][i]);
            }
            result.add(r1);
            result.add(r2);
        }
        return result;
    }
}
