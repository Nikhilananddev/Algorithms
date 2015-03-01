package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yu on 3/1/2015.
 */
public class Subsets {
    public static void test(String args[]) {
        int[] test = {1, 2, 3, 4};
        List<List<Integer>> answer = subsets(test);
        for (List<Integer> l: answer)
            System.out.println(l);
    }

    protected static void helper(int[] numbers, int startIdx, List<Integer> sol,  List<List<Integer>> answer) {
        if (startIdx == numbers.length) {
            List<Integer> solCopy = new ArrayList<Integer>();
            solCopy.addAll(sol);
            answer.add(solCopy);
            return;
        }
        // Case 1: does not pick current element
        helper(numbers, startIdx+1, sol, answer);
        // Case 2: pick current element
        sol.add(numbers[startIdx]);
        helper(numbers, startIdx + 1, sol, answer);
        sol.remove(sol.size() - 1);
    }

    public static List<List<Integer>> subsets(int[] S) {
        Arrays.sort(S); // Problem requires each solution is sorted, so sort the input
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        List<Integer> sol = new ArrayList<Integer>();
        helper(S, 0, sol, answer);
        return answer;
    }
}
