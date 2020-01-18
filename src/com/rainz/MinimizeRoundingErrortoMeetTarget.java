package com.rainz;

/*
 * Given an array of prices [p1,p2...,pn] and a target, round each price pi to Roundi(pi) so that the rounded array [Round1(p1),Round2(p2)...,Roundn(pn)] sums to the given target. Each operation Roundi(pi) could be either Floor(pi) or Ceil(pi).
 * Return the string "-1" if the rounded array is impossible to sum to target. Otherwise, return the smallest rounding error, which is defined as Σ |Roundi(pi) - (pi)| for i from 1 to n, as a string with three places after the decimal.
 * 1 <= prices.length <= 500.
 * Each string of prices prices[i] represents a real number which is between 0 and 1000 and has exactly 3 decimal places.
 * target is between 0 and 1000000.
 */
public class MinimizeRoundingErrortoMeetTarget {
    public static void test(String args[]) {
        String[] prices1 = {"0.700","2.800","4.900"};
        int target1 = 8;
        System.out.println(minimizeError(prices1, target1));
        String[] prices2 = {"1.500","2.500","3.500"};
        int target2 = 10;
        System.out.println(minimizeError(prices2, target2));
    }

    private static int helperSlow(String[] prices, int start, int rounding, int target) {
        if (start >= prices.length) {
            if (target != 0)
                return -1;
            return rounding;
        }
        String p = prices[start];
        int decIdx = p.indexOf('.');
        int intPart = Integer.parseInt(p.substring(0, decIdx));
        int decPart = Integer.parseInt(p.substring(decIdx+1));
        int roundingNew = helperSlow(prices, start+1, rounding+decPart, target-intPart); // floor
        if (decPart != 0) {
            int rCeil = helperSlow(prices, start+1, rounding+(1000-decPart), target-intPart-1); // ceiling
            if (roundingNew == -1 || (rCeil >= 0 && rCeil < roundingNew))
                roundingNew = rCeil;
        }
        return roundingNew;
    }

    public static String minimizeErrorSlow(String[] prices, int target) {
        int rounding = helperSlow(prices, 0, 0, target);
        if (rounding < 0)
            return "-1";
        return String.format("%d.%03d", rounding/1000, rounding%1000);
    }

    public static String minimizeError(String[] prices, int target) {
        // First remove integer part and count prices with non-zero decimals
        int N = prices.length;
        int decCount = 0;
        int[] decimals = new int[N];
        // Part integer and decimal parts for each number
        for (int i = 0; i < N; ++i) {
            String p = prices[i];
            int decIdx = p.indexOf('.');
            target -= Integer.parseInt(p.substring(0, decIdx)); // remove integer part from target
            int decPart = Integer.parseInt(p.substring(decIdx+1));
            if (decPart > 0)
                ++decCount;
            decimals[i] = decPart;
        }
        /*
         * If the integer parts already exceeds target,
         * or if rounding up all decimal parts is still not enough,
         * then there will be no solution.
         */
        if (target < 0 || decCount < target)
            return "-1";
        int[][] dp = new int[N+1][target+1];
        // We calculate rounding errors only for those with a solution. Leave the rest at 0.
        // If target is 0, round everything down and add the errors
        for (int i = 1; i <= N; ++i) {
            dp[i][0] =  dp[i-1][0] + decimals[i-1];
        }
        for (int t = 1; t <= target; ++t) {
            // We must pick at least t decimals for sum=t
            dp[t][t] = dp[t-1][t-1] + (1000 - decimals[t-1]); // round up t decimals to 1 and add errors
            for (int i = t+1; i <= N; ++i) {
                dp[i][t] = Math.min(
                        dp[i-1][t] + decimals[i-1],        // round down
                        dp[i-1][t-1] + (1000 - decimals[i-1]) // round up
                );
            }
        }
        int ans = dp[N][target];
        return String.format("%d.%03d", ans/1000, ans%1000);
    }
}
