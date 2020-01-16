package com.rainz;

/*
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.
 * Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.
 */
public class NumberofDiceRollsWithTargetSum {
    public static void test(String args[]) {
        System.out.println(numRollsToTarget(1, 6, 3));
        System.out.println(numRollsToTarget(2, 6, 7));
        System.out.println(numRollsToTarget(2, 5, 10));
        System.out.println(numRollsToTarget(1, 2, 3));
        System.out.println(numRollsToTarget(30, 30, 500));
    }
    public static int numRollsToTarget(int d, int f, int target) {
        final int MODULO = 1000000007;
        int[] ways = new int[target+1];  // # ways for each target
        // Result for roll #1
        for (int t = 1; t <= target; ++t)
            ways[t] = t > f ? 0 : 1;
        for (int roll = 2; roll <= d; ++roll) {
            int[] newWays = new int[target+1];  // # ways for each target
            for (int t = 1; t <= target; ++t) {
                for (int face = 1; face <= f; ++face) {
                    newWays[t] += t > face ? (ways[t - face] % MODULO) : 0;
                    newWays[t] %= MODULO;
                }
            }
            ways = newWays;
        }
        return ways[target];
    }
}
