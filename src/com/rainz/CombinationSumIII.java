package com.rainz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * Note:
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 */

public class CombinationSumIII {
    public static void test(String args[]) {
        Main.printList2D(combinationSum3(3, 7));
        Main.printList2D(combinationSum3(3, 9));
    }

    private static void helper(int k, int n, int start, Set<Integer> sol, List<List<Integer>> solutions) {
        if (k == 0) {
            if (n == 0) {
                List<Integer> l = new ArrayList<>();
                l.addAll(sol);
                solutions.add(l);
            }
            return;
        }
        // Note: without "start", same solution will be added multiple times
        for (int i = start; i <= 9; ++i) {
            if (i > n)
                continue;
            /*if (sol.contains(i))
                continue; */
            sol.add(i);
            helper(k-1, n - i, i + 1, sol, solutions);
            sol.remove(i);
        }
    }
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> sols = new ArrayList<>();
        Set<Integer> sol = new HashSet<>();
        helper(k, n, 1, sol, sols);
        return sols;
    }
}
