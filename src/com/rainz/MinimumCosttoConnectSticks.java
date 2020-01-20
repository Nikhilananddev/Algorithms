package com.rainz;

import java.util.PriorityQueue;
import java.util.Queue;

/*
 * You have some sticks with positive integer lengths.
 * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 */
public class MinimumCosttoConnectSticks {
    public static void test(String args[]) {
        int[] input1 = {2,4,3};
        System.out.println(connectSticks(input1));
        int[] input2 = {1,8,3,5};
        System.out.println(connectSticks(input2));
    }

    public static int connectSticks(int[] sticks) {
        Queue<Integer> pq = new PriorityQueue<>();
        for (int n: sticks)
            pq.add(n);

        int ans = 0;
        while (pq.size() > 1) {
            int cost = pq.remove() + pq.remove();
            ans += cost;
            pq.add(cost);
        }
        return ans;
    }
}
