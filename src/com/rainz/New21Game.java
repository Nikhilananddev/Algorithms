package com.rainz;

/*
 * Alice plays the following game, loosely based on the card game "21".
 * Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.
 * Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?
 */
public class New21Game {
    public static void test(String args[]) {
        System.out.println(new21Game(10, 1, 10));
        System.out.println(new21Game(6, 1, 10));
        System.out.println(new21Game(21, 17, 10));
        System.out.println(new21Game(6746, 5689, 6186));
    }

    public static double new21Game(int N, int K, int W) {
        // Max possible points is getting largest card (W) when we have K-1 points: W+K-1
        double[] dp = new double[W+K];
        // Base case: when we have K or more points, we stop.
        int p;
        for (p = dp.length-1; p >= K; --p) {
            dp[p] = p > N ? 0 : 1;
        }
        // Computer sum of the last W results for use later. Without this we get time limit exceed
        double sumW = 0;
        for (int i = 0; i < W; ++i)
            sumW += dp[p+1+i];
        // When we have less than K points, we need to get more cards.
        for (p = K - 1; p >= 0; --p) {
            // Computer avg of last W results
            dp[p] = sumW/W;
            // Update last W results with dp[p]
            sumW = sumW + dp[p] - dp[p+W];
        }
        return dp[0];
    }
}
