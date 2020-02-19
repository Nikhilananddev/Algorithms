package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 */
public class FindAllDuplicatesinanArray {
    public static void test(String args[]) {
        int[] input1 = {4,3,2,7,8,2,3,1};
        System.out.println(findDuplicates(input1));
    }

    /*
     * For each n, flag the number at index n-1 negative just to indicate we've seen it.
     * So if we see n again, the number at n-1 is already negative, and we know it's a dup.
     * Note: the negative-ness of an element at i is not related to the number itself.
     * It's used only to indicate we've seen the value i+1, which is mostly likely a number elsewhere.
     */
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int n: nums) {
            int idx = Math.abs(n) - 1;
            if (nums[idx] < 0)
                ans.add(idx+1);
            nums[idx] = -nums[idx];
        }
        return ans;
    }
}
