package com.rainz;

import java.util.*;

/*
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 * If there is no non-empty subarray with sum at least K, return -1.
 */
public class ShortestSubarraywithSumatLeastK {
    public static void test(String args[]) {
        int[] input1 = {1};
        System.out.println(shortestSubarray(input1, 1));
        int[] input2 = {1,2};
        System.out.println(shortestSubarray(input2, 4));
        int[] input3 = {2,-1,2};
        System.out.println(shortestSubarray(input3, 3));
    }

    public static int shortestSubarray(int[] A, int K) {
        return shortestSubarrayPQ(A, K);
    }
    /*
     * O(n): use a deque to store candidate start idx and its prefix sum.
     * Prefix sum in deque must be strictly increasing, because
     * older & larger start sums will result in larger window with smaller sum.
     * When inserting a new candidate, pop out larger sums in the back.
     * Then start from front and try each candidate.
     * If candidate and i forms a windows with sum>=K, it can be dropped because
     * this is the smallest valid window it can form, future i's will be larger.
     */
    public static int shortestSubarrayDQ(int[] A, int K) {
        Deque<int[]> dq = new LinkedList<>(); // store candidate window starts
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; ++i) {
            sum += A[i];
            if (sum >= K)
                ans = Math.min(ans, i+1); // take care of the case where window starts from beginning
            // Sums in deque are increasing
            while (!dq.isEmpty() && sum < dq.peekLast()[0])
                dq.removeLast();
            // Now go through candidate starts to find min subarray
            // Start from front of deque you can only remove all front or all back, but candidates are basically in front
            while (!dq.isEmpty() && sum - dq.peekFirst()[0] >= K) {
                int[] candidate = dq.removeFirst();
                ans = Math.min(ans, i - candidate[1]);
            }
            int[] rec = {sum, i};
            dq.add(rec);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /*
     * O(nlogn): use a priority queue to store prefix sum. Easier to understand.
     * Store candidate starts in a PQ sorted by prefix sums.
     * For each i, just pick out the smallest prefix sums and find the smallest valid window ending with i.
     * As with the deque solution, candidates can be dropped once a valid window is found,
     * because future i's result in larger window
     */
    public static int shortestSubarrayPQ(int[] A, int K) {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x->x[0])); // store candidate window starts
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; ++i) {
            sum += A[i];
            if (sum >= K)
                ans = Math.min(ans, i + 1); // take care of the case where window starts from beginning
            while(!pq.isEmpty() && sum - pq.peek()[0] >= K) {
                int[] candidate = pq.remove();
                int winSize = i - candidate[1];
                ans = Math.min(ans, winSize);
            }
            int[] rec = {sum, i};
            pq.add(rec);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
