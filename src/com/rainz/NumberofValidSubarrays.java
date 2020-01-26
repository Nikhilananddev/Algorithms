package com.rainz;

import java.util.Arrays;
import java.util.Stack;

/*
 * Given an array A of integers, return the number of non-empty continuous subarrays that satisfy the following condition:
 * The leftmost element of the subarray is not larger than other elements in the subarray.
 */
public class NumberofValidSubarrays {
    public static void test(String args[]) {
        int[] input1 = {1,4,2,5,3};
        System.out.println(validSubarrays(input1));
        int[] input2 = {3,2,1};
        System.out.println(validSubarrays(input2));
        int[] input3 = {2,2,2};
        System.out.println(validSubarrays(input3));
    }

    public static int validSubarrays(int[] nums) {
        int N = nums.length;
        int[] rightEdges = new int[N];
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < N; ++i) {
            int n = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] > n) {
                rightEdges[stk.pop()] = i;
            }
            stk.push(i);
        }
        while (!stk.isEmpty()) {
            rightEdges[stk.pop()] = N;
        }
        int ans = 0;
        for (int i = 0; i < N; ++i) {
            ans += rightEdges[i] - i;
        }
        return ans;
    }
}
