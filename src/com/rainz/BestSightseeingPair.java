package com.rainz;

public class BestSightseeingPair {
    public static void test(String args[]) {
        int[] input = {8,1,5,2,6};
        System.out.println(bestSightseeingPair(input));
    }

    public static int bestSightseeingPair(int[] A) {
        final int N = A.length;
        int[] ai = new int[N];
        int[] aj = new int[N];
        for (int i = 0; i < N; ++i) {
            ai[i] = A[i] + i;
            aj[i] = A[i] - i;
        }
        int[] maxJs = new int[N];
        maxJs[N-1] = aj[N-1];
        for (int i = N-2; i >= 0; --i) {
            maxJs[i] = maxJs[i+1] > aj[i] ? maxJs[i+1] : aj[i];
        }
        int maxScore = Integer.MIN_VALUE;
        for (int i = 0; i < N-1; ++i) {
            int score = ai[i] + maxJs[i+1];
            if (score > maxScore)
                maxScore = score;
        }

        return maxScore;
    }
}
