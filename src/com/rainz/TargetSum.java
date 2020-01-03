package com.rainz;

/*
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 */

import java.util.HashMap;
import java.util.Map;

public class TargetSum {
    public static void test(String args[]) {
        int[] input1 = {1, 1, 1, 1, 1};
        System.out.println(findTargetSumWays(input1, 3));
        int[] input12 = {1};
        System.out.println(findTargetSumWays(input1, 2));
    }

    public static int findTargetSumWays(int[] nums, int S) {
        Map<Integer, Integer> ways = new HashMap<>();
        ways.put(0, 1); // for s=0;
        // For each number, create a new table and store all possible values and their counts
        for (int num: nums) {
            Map<Integer, Integer> waysNext = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry: ways.entrySet()) {
                int target = entry.getKey();
                int wayCount = entry.getValue();
                int newTarget = target + num;
                waysNext.put(newTarget, waysNext.getOrDefault(newTarget, 0)+wayCount);
                newTarget = target - num;
                waysNext.put(newTarget, waysNext.getOrDefault(newTarget, 0)+wayCount);
            }
            ways = waysNext;
        }
        return ways.getOrDefault(S, 0);
    }
}
