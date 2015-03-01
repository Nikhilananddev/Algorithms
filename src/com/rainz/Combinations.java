package com.rainz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yu on 2/24/2015.
 */
public class Combinations {
    public static void test(String args[]) {
        List<List<Integer>> answer = combine(4, 2);
        for (List<Integer> combo: answer)
            System.out.println(combo);
    }

    protected static void helper(int k, int[] numbers, int startIdx, List<Integer> sol,  List<List<Integer>> answer) {
        int numsLeft = numbers.length - startIdx;
        if (k == 0 || k == numsLeft) {
            List<Integer> solCopy = new ArrayList<Integer>();
            solCopy.addAll(sol);
            if (k == numsLeft) {
                for (int i = startIdx; i < numbers.length; ++i)
                    solCopy.add(numbers[i]);
            }
            answer.add(solCopy);
            return;
        }
        if (k > numsLeft)
            return;
        // Case 1: pick current element
        sol.add(numbers[startIdx]);
        helper(k - 1, numbers, startIdx + 1, sol, answer);
        sol.remove(sol.size()-1);
        // Case 2: does not pick current element
        helper(k, numbers, startIdx+1, sol, answer);
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        List<Integer> sol = new ArrayList<Integer>();
        int[] numbers = new int[n];
        for (int i = 0; i < n; ++i)
            numbers[i] = i+1;
        helper(k, numbers, 0, sol, answer);
        return answer;
    }
}
