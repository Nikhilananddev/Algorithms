package com.rainz;

/*
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
 */

import java.util.Arrays;

public class QueueReconstructionbyHeight {
    public static void test(String args[]) {
        int[][] input = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        Main.printArray2D(reconstructQueue(input));
    }

    /*
     * First, sort by height (h). For same height, sort by #ppl taller (k).
     * Next, for each person, if the slot is empty, his height > my height.
     * So, decrement k for each empty slot or if height > my height.
     * when k==0, find the next empty slot and put this person in.
     */

    public static int[][] reconstructQueue(int[][] people) {
        int N = people.length;
        Arrays.sort(people, (x, y) -> x[0]!=y[0] ? Integer.compare(x[0], y[0]) : Integer.compare(x[1], y[1]));

        int[][] ans = new int[N][2];
        for(int[] a: ans)
            a[1] = -1; // initialize to invalid
        for (int[] p: people) {
            int k = p[1];
            int idx = 0;
            while (idx < ans.length) {
                int[] a = ans[idx];
                if (a[1] == -1 && k == 0)
                    break; // found the slot
                if (a[1] == -1 || a[0] >= p[0])
                    --k;
                ++idx;
            }
            ans[idx][0] = p[0];
            ans[idx][1] = p[1];
        }
        return ans;
    }
}
