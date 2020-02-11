package com.rainz;

/*
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * If it cannot be done, return -1.
 */
public class MinimumDominoRotationsForEqualRow {
    public static void test(String args[]) {
        int[] A1 = {2,1,2,4,2,2}, B1 = {5,2,6,2,3,2};
        System.out.println(minDominoRotations(A1, B1));
        int[] A2 = {3,5,1,2,3}, B2 = {3,6,3,3,4};
        System.out.println(minDominoRotations(A2, B2));
    }
    public static int minDominoRotations(int[] A, int[] B) {
        int L = A.length;
        int[] aCount = new int[7];
        int[] bCount = new int[7];
        int[] abCount = new int[7];
        for (int i = 0; i < L; ++i) {
            int a = A[i], b = B[i];
            ++aCount[a];
            ++bCount[b];
            if (a == b)
                ++abCount[a];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= 6; ++i) {
            if (aCount[i] + bCount[i] - abCount[i] < L)
                continue;
            int swap = Math.min(aCount[i], bCount[i]) - abCount[i];
            ans = Math.min(ans, swap);
        }
        if (ans == Integer.MAX_VALUE)
            ans = -1;
        return ans;
    }
}
