package com.rainz;

import java.util.Stack;

/*
 * Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 */
public class SumofSubarrayMinimums {
    public static void test(String args[]) {
        int[] input1 = {3,1,2,4};
        System.out.println(sumSubarrayMins(input1));
    }

    /*
     * Idea is to count # times each element will show up as min.
     * For each num, find left & right boundaries where everything is greater, using the O(n) stack method.
     * If two elements in a window are equal, left most one wins to avoid double counting.
     */
    public static int sumSubarrayMins(int[] A) {
        final int MODULO = 1000000007;
        int N = A.length;
        int[] rightRange = new int[N];
        // Find first smaller # on the right; if equal, left most one wins
        int idx = 0;
        Stack<Integer> stk = new Stack<>();
        while (idx < N) {
            while (!stk.isEmpty() && A[stk.peek()] > A[idx]) {
                int prev = stk.pop();
                rightRange[prev] = idx - 1;
            }
            stk.push(idx);
            ++idx;
        }
        while (!stk.isEmpty())
            rightRange[stk.pop()] = N - 1;

        int[] leftRange = new int[N];
        // Find first smaller # on the left; if equal, left most one wins
        idx = N - 1;
        stk.clear();
        while (idx >= 0) {
            while (!stk.isEmpty() && A[stk.peek()] >= A[idx]) {
                int prev = stk.pop();
                leftRange[prev] = idx + 1;
            }
            stk.push(idx);
            --idx;
        }
        while (!stk.isEmpty())
            leftRange[stk.pop()] = 0;

        int ans = 0;
        for (int i = 0; i < N; ++i) {
            int count = (rightRange[i] - i + 1)*(i - leftRange[i] + 1);
            ans = (ans + A[i] * count) % MODULO;
        }
        return ans;
    }
}
