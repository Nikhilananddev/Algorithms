package com.rainz;

import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 */
public class KthSmallestElementinaSortedMatrix {
    public static void test(String args[]) {
        int[][] input1 = {
                             { 1,  5,  9},
                             {10, 11, 13},
                             {12, 13, 15}
                         };
        System.out.println(kthSmallest(input1, 8));
    }

    /*
     * Use a priority queue. It is *not* guaranteed to hold top k elements.
     * We just need to ensure the smallest number is always in the pq.
     * So, we add the top-left cell first. Then, if we are at left column,
     * we add both the right and bottom cells. Otherwise we only add right cell.
     */

    public static int kthSmallest(int[][] matrix, int k) {
        int R = matrix.length;
        if (R == 0)
            return -1;
        int C = matrix[0].length;

        Queue<int[]> pq = new PriorityQueue<>(
                (x, y) -> Integer.compare( matrix[x[0]][x[1]], matrix[y[0]][y[1]] )
        );

        int[] topLeft = {0, 0};
        pq.add(topLeft);
        int[] ans = null;
        while (k > 0 && !pq.isEmpty()) {
            ans = pq.remove();
            if (ans[1] < C-1) {
                // Add element from right
                int[] cell = {ans[0], ans[1]+1};
                pq.add(cell);
            }
            if (ans[1] == 0 && ans[0] < R-1) {
                // Add element from below
                int[] cell = {ans[0]+1, ans[1]};
                pq.add(cell);
            }
            --k;
        }
        if (k != 0)
            return -1;
        return matrix[ans[0]][ans[1]];
    }
}
