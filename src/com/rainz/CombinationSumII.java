package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yu on 2/1/2015.
 */

/*
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 */

public class CombinationSumII {
    public static void test(String args[]) {
        int[] test = {10,1,2,7,6,1,5};
        List<List<Integer>> answer = combinationSum2(test, 8);
        for (List<Integer> sol: answer) {
            System.out.print("Solution: ");
            for (Integer i: sol) {
                System.out.print("" + i + " ");
            }
            System.out.println();
        }
    }

    protected static void helper(int[] num, int numStart, int target, List<Integer> solution, List<List<Integer>> answer) {
        if (target == 0) {
            List<Integer> solCopy = new ArrayList<Integer>();
            solCopy.addAll(solution);
            answer.add(solCopy);
            return;
        }
        for (int i = numStart; i< num.length; ++i) {
            int x = num[i];
            if (i > numStart && x == num[i-1])
                continue;
            if (x > target)
                break;
            solution.add(x);
            helper(num, i+1, target-x, solution, answer);
            solution.remove(solution.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        List<Integer> solution = new ArrayList<Integer>();
        helper(num, 0, target, solution, answer);
        return answer;
    }
}
