package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Numbers can be regarded as product of its factors. For example,
 * 8 = 2 x 2 x 2;
 * = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * Note:
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 */
public class FactorCombinations {
    public static void test(String args[]) {
        Main.printList2D(getFactors(1));
        Main.printList2D(getFactors(37));
        Main.printList2D(getFactors(12));
        Main.printList2D(getFactors(32));
    }

    private static void helper(int n, int start, List<Integer> factors, List<List<Integer>> ans) {
        for (int i = start; i*i <= n; ++i) {
            if (n % i != 0)
                continue;
            factors.add(i);
            if (n > i) {
                List<Integer> sol = new ArrayList<>();
                sol.addAll(factors);
                sol.add(n/i);
                ans.add(sol);
            }
            helper(n/i, i, factors, ans);
            factors.remove(factors.size()-1);
        }
    }

    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> factors = new ArrayList<>();
        helper(n, 2, factors, ans);
        return ans;
    }

    public static List<List<Integer>> getFactorsPass(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 2; i*i <= n; ++i) {
            if (n % i != 0)
                continue;
            List<Integer> base = new ArrayList<>();
            base.add(i);
            base.add(n/i);
            ans.add(base);
            List<List<Integer>> res = getFactors(n/i);
            for (List<Integer> r: res) {
                if (i <= r.get(0)) {
                    List<Integer> factors = new ArrayList<>();
                    factors.add(i);
                    factors.addAll(r);
                    ans.add(factors);
                }
            }
        }
        return ans;
    }
}
