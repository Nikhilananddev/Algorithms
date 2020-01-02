package com.rainz;

import java.util.Arrays;
import java.util.Stack;

/*
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 */
public class NextGreaterElementII {
    public static void test(String args[]) {
        int[] nums = {1, 2, 1};
        Main.printArray(nextGreaterElements(nums));
    }

    public static int[] nextGreaterElements(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        Arrays.fill(ans, -1);
        Stack<Integer> stk = new Stack<>();
        for (int i = 2*N-1; i >= 0; --i) {
            int n = nums[i%N];
            while (!stk.isEmpty()) {
                if (n < stk.peek()) {
                    if (i < N)
                        ans[i] = stk.peek();
                    break;
                }
                stk.pop();
            }
            stk.push(n);
        }
        return ans;
    }
}
