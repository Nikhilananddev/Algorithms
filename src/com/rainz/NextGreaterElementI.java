package com.rainz;

/*
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
 * All elements in nums1 and nums2 are unique.
 * The length of both nums1 and nums2 would not exceed 1000.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElementI {
    public static void test(String args[]) {
        int[] nums11 = {4,1,2};
        int[] nums12 = {1,3,4,2};
        Main.printArray(nextGreaterElement(nums11, nums12));
        int[] nums21 = {2,4};
        int[] nums22 = {1,2,3,4};
        Main.printArray(nextGreaterElement(nums21, nums22));

    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Map<Integer, Integer> idxTable = new HashMap<>();
        int[] nextLarge = new int[nums2.length];
        Stack<Integer> stk = new Stack<>();
        for (int i = nums2.length-1; i >= 0; --i) {
            int n = nums2[i];
            idxTable.put(n, i); // for looking up indices from nums1
            nextLarge[i] = -1;
            while (!stk.isEmpty()) {
                if (n < stk.peek()) {
                    nextLarge[i] = stk.peek();
                    break;
                }
                stk.pop();
            }
            stk.push(n);
        }
        for (int i = 0; i < nums1.length; ++i)
            ans[i] = nextLarge[idxTable.get(nums1[i])];
        return ans;
    }
}
