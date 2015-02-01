package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yu on 2/1/2015.
 */
public class CombinationSum {
    public static void test(String args[]) {
        int[] test = {2,3,6,7};
        List<List<Integer>> answer = combinationSum(test, 7);
        for (List<Integer> sol: answer) {
            System.out.print("Solution: ");
            for (Integer i: sol) {
                System.out.print("" + i + " ");
            }
            System.out.println();
        }
    }

    protected static void helper(int[] candidates, int candidateStart, int target, List<Integer> solution, List<List<Integer>> answer) {
        if (target == 0) {
            List<Integer> solCopy = new ArrayList<Integer>();
            solCopy.addAll(solution);
            answer.add(solCopy);
            return;
        }
        for (int i = candidateStart; i< candidates.length; ++i) {
            int x = candidates[i];
            if (x > target)
                break;
            solution.add(x);
            helper(candidates, i, target-x, solution, answer);
            solution.remove(solution.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        List<Integer> solution = new ArrayList<Integer>();
        helper(candidates, 0, target, solution, answer);
        return answer;
    }
}
