package com.rainz;


public class LargestSumOfAverages {
    public static void test(String args[]) {
        int[] input = {9,1,2,3,9};
        System.out.println(largestSumOfAverages(input, 3));
    }

    public static double largestSumOfAverages(int[] A, int K) {
        final int N = A.length;
        double[] sums = new double[N+1];
        sums[0] = 0;
        for (int i = 0; i < N; ++i)
            sums[i+1] = A[i] + sums[i];

        double[][] dp = new double[N][K];
        for (int i = 0; i < N; ++i)
            dp[i][0] = (sums[N] - sums[i]) / (N - i);
        for (int k = 1; k < K; ++k) {
            for (int i = 0; i < N - 1; ++i) {
                for (int j = i + 1; j < N; ++j) {
                    double newDp = (sums[j] - sums[i]) / (j - i) + dp[j][k - 1];
                    if (dp[i][k] < newDp)
                        dp[i][k] = newDp;
                }
            }
        }
        return dp[0][K - 1];
    }
}
