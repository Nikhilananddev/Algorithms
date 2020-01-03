package com.rainz;

import java.util.Stack;

/*
 * Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed is a permutation of popped.
 * pushed and popped have distinct values.
 */

public class ValidateStackSequences {
    public static void test(String args[]) {
        int[] pushed1 = {1,2,3,4,5};
        int[] popped1 = {4,5,3,2,1};
        System.out.println(validateStackSequences(pushed1, popped1));
        int[] pushed2 = {1,2,3,4,5};
        int[] popped2 = {4,3,5,1,2};
        System.out.println(validateStackSequences(pushed2, popped2));
    }


    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        int pushIdx = 0;
        int popIdx = 0;
        Stack<Integer> stk = new Stack<>();
        while (popIdx < popped.length) {
            if (pushIdx < pushed.length && (stk.isEmpty() || stk.peek() != popped[popIdx])) {
                stk.push(pushed[pushIdx++]);
            }
            else {
                if (popped[popIdx] != stk.pop())
                    break;
                ++popIdx;
            }
        }
        return popIdx == popped.length;
    }
}
