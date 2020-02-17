package com.rainz;

/*
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
    public static void test(String args[]) {
        int[] input1 = {1,3,-1,-3,5,3,6,7};
        Main.printArray(medianSlidingWindow(input1, 3));
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        double[] ans = new double[N-k+1];
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        int start = 0, end = 0; // end is exclusive
        int idx = 0;
        while (end < N) {
            ++end;
            int endNum = nums[end-1];
            if (maxPQ.isEmpty() || maxPQ.peek() > endNum)
                maxPQ.offer(endNum);
            else
                minPQ.offer(endNum);
            if (end - start > k) {
                int startNum = nums[start];
                if (!maxPQ.isEmpty() && maxPQ.peek() >= startNum)
                    maxPQ.remove(startNum);
                else
                    minPQ.remove(startNum);
                ++start;
            }

            // Balance PQs
            if (maxPQ.size() >= minPQ.size()) {
                while (maxPQ.size() > minPQ.size()+1)
                    minPQ.offer(maxPQ.poll());
            } else {
                while (maxPQ.size() < minPQ.size())
                    maxPQ.offer(minPQ.poll());
            }

            if (end - start == k) {
                if (k % 2 == 1)
                    ans[idx++] = maxPQ.peek();
                else
                    ans[idx++] = ((double)maxPQ.peek() + minPQ.peek()) / 2.0;
            }
        }
        return ans;
    }
}
