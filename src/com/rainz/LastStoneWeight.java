package com.rainz;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * We have a collection of rocks, each rock has a positive integer weight.
 * Each turn, we choose the two heaviest rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 */
public class LastStoneWeight {
    public static void test(String args[]) {
        int[] input1 = {2,7,4,1,8,1};
        System.out.println(lastStoneWeight(input1));
    }


    public static int lastStoneWeight(int[] stones) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int n: stones)
            pq.add(n);
        int ans = 0;
        while (!pq.isEmpty()) {
            int heavy1 = pq.remove();
            if (pq.isEmpty()) {
                ans = heavy1;
                break;
            }
            int heavy2 = pq.remove();
            if (heavy1 != heavy2)
                pq.add(heavy1 - heavy2);
        }
        return ans;
    }
}
