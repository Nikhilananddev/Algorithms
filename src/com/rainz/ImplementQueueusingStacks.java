package com.rainz;

import java.util.Stack;

public class ImplementQueueusingStacks {
    public static void test(String args[]) {
        ImplementQueueusingStacks queue = new ImplementQueueusingStacks();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());  // returns 1
        System.out.println(queue.pop());   // returns 1
        System.out.println(queue.empty()); // returns false
    }

    // Remember you cannot transfer data from stk1 to stk2 unless stk2 is empty
    private Stack<Integer> stk1 = new Stack<>();
    private Stack<Integer> stk2 = new Stack<>();

    /** Initialize your data structure here. */
    public ImplementQueueusingStacks() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stk1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stk2.isEmpty()) {
            while (!stk1.isEmpty())
                stk2.push(stk1.pop());
        }
        return stk2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (stk2.isEmpty()) {
            while (!stk1.isEmpty())
                stk2.push(stk1.pop());
        }
        return stk2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stk1.isEmpty() && stk2.isEmpty();
    }

}
