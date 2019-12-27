package com.rainz;

import java.util.Deque;
import java.util.LinkedList;

/*
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 */
public class SlidingWindowMaximum {
    public static void test(String args[]) {
        int[] input = {1,3,-1,-3,5,3,6,7};
        Main.printArray(maxSlidingWindow(input, 3));
    }

    /*
     * Use a deque to keep numbers which are "later" and "larger".
     * Every new number will remove previous smaller numbers, until it hits a larger one.
     * Numbers in deque are in decreasing order as a result.
     * The head in the deque is the max in window.
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0)
            return new int[0];
        int[] ans = new int[nums.length-k+1];
        Deque<Integer> deq = new LinkedList<>();
        int start = 0;
        int end = 0; // exclusive
        while (end < nums.length) {
            ++end;
            int newNum = nums[end-1];
            while (!deq.isEmpty() && nums[deq.peekLast()] < newNum)
                deq.removeLast();
            deq.addLast(end-1);
            if (end - start >= k) {
                ans[start++] = nums[deq.peekFirst()];
                if (start > deq.peekFirst())
                    deq.removeFirst();
            }
        }
        return ans;
    }
}
