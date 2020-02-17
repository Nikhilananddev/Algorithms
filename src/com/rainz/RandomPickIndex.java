package com.rainz;

import java.util.*;

/*
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 */
public class RandomPickIndex {
    public static void test(String args[]) {
        int[] nums = new int[] {1,2,3,3,3};
        RandomPickIndex solution = new RandomPickIndex(nums);
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(1));
    }

    Random rand = new Random();

    // For hashmap solution only
    private Map<Integer, List<Integer>> map = new HashMap<>();

    // For reservoir sampling solution only
    private int[] _nums;

    public RandomPickIndex(int[] nums) {
        // For hashmap solution only
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            map.putIfAbsent(num, new ArrayList<>());
            map.get(num).add(i);
        }

        // For reservoir sampling solution only
        _nums = nums;
    }

    // For reservoir sampling solution only
    public int pick(int target) {
        int cnt = 0, res = -1;
        for (int i = 0; i < _nums.length; ++i) {
            if (_nums[i] != target)
                continue;
            ++cnt;
            if (rand.nextInt(cnt) == 0)
                res = i;
        }
        return res;
    }

    // For hashmap solution only
    public int pickHash(int target) {
        List<Integer> list = map.get(target);
        int n = list.size();
        return list.get(rand.nextInt(n));
    }
}
