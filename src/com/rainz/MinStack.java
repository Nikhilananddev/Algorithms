package com.rainz;

import java.util.Stack;

public class MinStack {
    public static void test(String args[]) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());    // --> Returns -3.
        minStack.pop();
        System.out.println(minStack.top());       // --> Returns 0.
        System.out.println(minStack.getMin());    // --> Returns -2.
    }

    private Stack<int[]> stk = new Stack<>();

    public MinStack() {

    }

    public void push(int x) {
        int min = Integer.MAX_VALUE;
        if (!stk.isEmpty()) {
            int[] top = stk.peek();
            min = top[1];
        }
        if (min > x)
            min = x;
        int[] newTop = {x, min};
        stk.push(newTop);
    }

    public void pop() {
        stk.pop();
    }

    public int top() {
        return stk.peek()[0];
    }

    public int getMin() {
        return stk.peek()[1];
    }
}
