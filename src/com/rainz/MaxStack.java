package com.rainz;

/*
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 */

import java.util.Stack;

public class MaxStack {
    public static void test(String args[]) {
        MaxStack stack = new MaxStack();
//        stack.push(5);
//        stack.push(1);
//        stack.push(5);
//        System.out.println(stack.top()); // -> 5
//        System.out.println(stack.popMax()); // -> 5
//        System.out.println(stack.top()); // -> 1
//        System.out.println(stack.peekMax()); // -> 5
//        System.out.println(stack.pop()); // -> 1
//        System.out.println(stack.top()); // -> 5

        stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        System.out.println(stack.popMax()); // -> 5
        System.out.println(stack.peekMax()); // -> 1
    }

    private Stack<int[]> _stk = new Stack<>();

    public MaxStack() {

    }

    public void push(int x) {
        int max = _stk.isEmpty() ? x : Math.max(x, _stk.peek()[1]);
        int[] rec = { x, max };
        _stk.push(rec);
    }

    public int pop() {
        return _stk.pop()[0];
    }

    public int top() {
        return _stk.peek()[0];
    }

    public int peekMax() {
        return _stk.peek()[1];
    }

    public int popMax() {
        Stack<int[]> tmpStk = new Stack<>();
        int max = _stk.peek()[1];
        while (_stk.peek()[0] != max)
            tmpStk.push(_stk.pop());
        _stk.pop();
        while (!tmpStk.isEmpty()) {
            // Note: you can't just push elements in tmpStk back to _stk
            // Because "max" has changed. Must call "push" to ensure new "max" values are set.
            int[] rec = tmpStk.pop();
            push(rec[0]);
        }
        return max;
    }
}
