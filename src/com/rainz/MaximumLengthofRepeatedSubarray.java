package com.rainz;

/*
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 */
public class MaximumLengthofRepeatedSubarray {
    public static void test(String args[]) {
        int[] A = {1,2,3,2,1};
        int[] B = {3,2,1,4,7};
        System.out.println(findLength(A, B));
    }

    public static int findLength(int[] A, int[] B) {
        // Build a DP array for common subarrays "ending here"
        int[][] dp = new int[A.length][B.length];
        for (int a = 0; a < A.length; ++a)
            dp[a][0] = A[a] == B[0] ? 1 : 0;
        for (int b = 1; b < B.length; ++b)
            dp[0][b] = A[0] == B[b] ? 1 : 0;
        int ans = 0;
        for (int a = 1; a < A.length; ++a) {
            for (int b = 1; b < B.length; ++b) {
                if (A[a] == B[b]) {
                    dp[a][b] = dp[a - 1][b - 1] + 1;
                    if (dp[a][b] > ans)
                        ans = dp[a][b];
                }
            }
        }
        return ans;
    }
}
