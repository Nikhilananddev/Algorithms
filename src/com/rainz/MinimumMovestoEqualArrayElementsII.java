package com.rainz;

import java.util.Arrays;

/*
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
 * You may assume the array's length is at most 10,000.
 */

public class MinimumMovestoEqualArrayElementsII {
    public static void test(String args[]) {
        int[] input = {1,2,3};
        System.out.println(minMoves2(input));
    }

    /* It's basically the median. For even # of nums, either one of the two is fine
     * Key is # of elements <= k equals that of elements > k.
     */
    public static int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int mid = nums[nums.length/2];
        int ans = 0;
        for (int n: nums)
            ans += Math.abs(mid-n);
        return ans;
    }
}
