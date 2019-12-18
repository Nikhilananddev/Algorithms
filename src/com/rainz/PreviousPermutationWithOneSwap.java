package com.rainz;

import java.util.Stack;

/*
 * Given an array A of positive integers (not necessarily distinct), return the lexicographically largest permutation that is smaller than A, that can be made with one swap (A swap exchanges the positions of two numbers A[i] and A[j]).  If it cannot be done, then return the same array.
 */
public class PreviousPermutationWithOneSwap {
    public static void test(String args[]) {
        int[] input1 = {3,2,1};
        Main.printArray(prevPermOpt1(input1));
        int[] input2 = {1,1,5};
        Main.printArray(prevPermOpt1(input2));
        int[] input3 = {1,9,4,6,7};
        Main.printArray(prevPermOpt1(input3));
        int[] input4 = {3,1,1,3};
        Main.printArray(prevPermOpt1(input4));
    }

    public static int[] prevPermOpt1(int[] A) {
        Stack<Integer> stk = new Stack<>();
        for (int i = A.length-1; i >= 0; --i) {
            if (i == A.length-1 || A[i] <= A[stk.peek()])
                stk.push(i);
            else {
                int exchangeIdx = -1;
                while (!stk.isEmpty()) {
                    int idx = stk.pop();
                    // Note the handling of equal case
                    if (A[idx] < A[i] &&
                            (exchangeIdx == -1 || A[idx] != A[exchangeIdx]))
                        exchangeIdx = idx;
                    else
                        break;
                }
                int tmp = A[i];
                A[i] = A[exchangeIdx];
                A[exchangeIdx] = tmp;
                break;
            }
        }
        return A;
    }
}
