package com.rainz;

/*
 * You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.
 * Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.
 */

public class TossStrangeCoins {
    public static void test(String args[]) {
        double[] input1 = {0.4};
        System.out.println(probabilityOfHeads(input1, 1));
        double[] input2 = {0.5,0.5,0.5,0.5,0.5};
        System.out.println(probabilityOfHeads(input2, 0));
    }

    public static double probabilityOfHeads(double[] prob, int target) {
        double[] prevDp = new double[target+1];
        prevDp[0] = 1; // when no coins are used, heads count will always be 0
        double[] dp = null;
        for (int i = 0; i < prob.length; ++i) {
            dp = new double[target+1];
            dp[0] = prevDp[0] * (1 - prob[i]); // prob for all tails
            for (int t = 1; t <= target; ++t) {
                dp[t] = prevDp[t] * (1 - prob[i]) + // got t heads in previous round, this round is tail
                        prevDp[t-1] * prob[i];      // got t-1 heads in previous round, this round is head
            }
            prevDp = dp;
        }
        return dp[target];
    }
}
