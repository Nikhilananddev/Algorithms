package com.rainz;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackusingQueues {
    public static void test(String args[]) {
        ImplementStackusingQueues stack = new ImplementStackusingQueues();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());   // returns 2
        System.out.println(stack.pop());   // returns 2
        System.out.println(stack.empty()); // returns false
    }

    /* Idea is every time we need to pop()/top(), move everything to second Q,
     * and grab the last one. The swap 1st Q and 2nd Q
     */

    private Queue<Integer> qIn = new LinkedList<>();
    private Queue<Integer> qOut = new LinkedList<>();

    private int transfer(boolean removeLast) {
        /* Transfer all but last element from qIn to qOut
         * Get the last item, then swap qIn & qOut
         */
        Integer last = null;
        int sz = qIn.size();
        for (int i = 0; i < sz; ++i) {
            last = qIn.remove();
            if (i < sz - 1 || !removeLast)
                qOut.add(last);
        }
        Queue<Integer> tmp = qIn;
        qIn = qOut;
        qOut = tmp;
        return last;
    }

    /** Initialize your data structure here. */
    public ImplementStackusingQueues() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        qIn.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return transfer(true);
    }

    /** Get the top element. */
    public int top() {
        return transfer(false);
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return qIn.isEmpty() && qOut.isEmpty();
    }
}
