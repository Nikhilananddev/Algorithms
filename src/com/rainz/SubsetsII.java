package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yu on 8/20/2016.
 */
public class SubsetsII {

    public static void test(String args[]) {
        //int[] test = {1, 2, 2};
        int[] test = {2, 2, 3};
        List<List<Integer>> answer = subsetsWithDup(test);
        for (List<Integer> l: answer)
            System.out.println(l);
    }

    protected static void helper(int[] numbers, int startIdx, List<Integer> sol,  List<List<Integer>> answer) {
        if (startIdx >= numbers.length) {
            List<Integer> solCopy = new ArrayList<Integer>();
            solCopy.addAll(sol);
            answer.add(solCopy);
            return;
        }
        // Case 1: does not pick current element
        int nextIdx = startIdx + 1;
        // If this item is not selected, don't select other item with same value
        while (nextIdx < numbers.length && numbers[nextIdx] == numbers[startIdx])
            ++nextIdx;
        helper(numbers, nextIdx, sol, answer);
        // Case 2: pick current element
        sol.add(numbers[startIdx]);
        helper(numbers, startIdx+1, sol, answer);
        sol.remove(sol.size() - 1);
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); // Problem requires each solution is sorted, so sort the input
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        List<Integer> sol = new ArrayList<Integer>();
        helper(nums, 0, sol, answer);
        return answer;
    }
}
