package com.rainz;

/*
 * Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
 */

public class MinimumASCIIDeleteSumforTwoStrings {
    public static void test(String args[]) {
        System.out.println(minimumDeleteSum("sea", "eat"));
        System.out.println(minimumDeleteSum("delete", "leet"));
        System.out.println(minimumDeleteSum("a", "at"));
    }

    /* This is a variation of longest common sequence problem */
    public static int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        // Initialize base cases
        dp[0][0] = 0;
        for (int i = 1; i <= s1.length(); ++i)
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
        for (int j = 1; j <= s2.length(); ++j)
            dp[0][j] = dp[0][j-1] + s2.charAt(j-1);

        for (int i = 1; i <= s1.length(); ++i) {
            for (int j = 1; j <= s2.length(); ++j) {
                char c1 = s1.charAt(i-1);
                char c2 = s2.charAt(j-1);
                if (c1 == c2)
                    dp[i][j] = dp[i-1][j-1];
                else {
                    int cost1 = dp[i-1][j] + c1; // delete last char in s1
                    int cost2 = dp[i][j-1] + c2; // delete last char in s2
                    dp[i][j] = Math.min(cost1, cost2);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
