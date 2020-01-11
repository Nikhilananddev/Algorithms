package com.rainz;

/*
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
 */
public class SquaresofaSortedArray {
    public static void test(String args[]) {
        int[] input1 = {-4,-1,0,3,10};
        Main.printArray(sortedSquares(input1));
        int[] input2 = {4,9,9,49,121};
        Main.printArray(sortedSquares(input2));
        int[] input3 = {-1};
        Main.printArray(sortedSquares(input3));
    }
    public static int[] sortedSquares(int[] A) {
        int N = A.length;
        int[] ans = new int[N];
        int posIdx;
        for (posIdx = 0; posIdx < N; ++posIdx) {
            if (A[posIdx] >= 0)
                break;
        }
        int negIdx = posIdx - 1; // if posIdx == N, negIdx will be N-1, which is correct
        for (int ansIdx = 0; ansIdx < N; ++ansIdx) {
            int posSq = posIdx < N ? A[posIdx] * A[posIdx] : -1;
            int negSq = negIdx >= 0 ? A[negIdx] * A[negIdx] : -1;
            if (negSq < 0 || (posSq >= 0 && posSq < negSq)) {
                ans[ansIdx] = posSq;
                ++posIdx;
            } else {
                ans[ansIdx] = negSq;
                --negIdx;
            }
        }
        return ans;
    }
}
