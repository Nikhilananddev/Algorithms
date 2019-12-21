package com.rainz;

/*
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].
 * Return any permutation of A that maximizes its advantage with respect to B.
 */

import java.util.Arrays;

public class AdvantageShuffle {
    public static void test(String args[]) {
        int[] A1 = {2,7,11,15};
        int[] B1 = {1,10,4,11};
        Main.printArray(advantageCount(A1, B1));

        int[] A2 = {12,24,8,32};
        int[] B2 = {13,25,32,11};
        Main.printArray(advantageCount(A2, B2));
    }

    public static int[] advantageCount(int[] A, int[] B) {
        int N = A.length;
        Arrays.sort(A);
        int[][] idxB = new int[N][2];
        for (int i = 0; i < N; ++i) {
            idxB[i][0] = B[i];
            idxB[i][1] = i; // save idx in B before soring here
        }
        Arrays.sort(idxB, (x, y) -> Integer.compare(x[0], y[0]));

        int[] ans = new int[N];
        int aMax = N - 1;
        int aMin = 0;
        int bIdx = N - 1;
        /* Try the largest in A with largest possible in B
           If B > A, use the smallest from A to match B, and try A against next largest in B.
         */
        while (aMin <= aMax) {
            int matchA = 0;
            if (A[aMax] > idxB[bIdx][0]) {
                // Largest A > largest B, use it
                matchA = aMax--;
            } else {
                // Largest A <= largest B, so use smallest possible to match B
                matchA = aMin++;
            }
            ans[idxB[bIdx][1]] = A[matchA];
            --bIdx;
        }

        return ans;
    }
}
