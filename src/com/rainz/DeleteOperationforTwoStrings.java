package com.rainz;

/*
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.
 */
public class DeleteOperationforTwoStrings {
    public static void test(String args[]) {
        System.out.println(minDistance("sea", "eat"));
    }

    /* This is a variation of Longest Common Subsequence */
    public static int minDistance(String word1, String word2) {
        int N1 = word1.length(), N2 = word2.length();
        int[][] dp = new int[N1+1][N2+1];
        for (int i = 0; i <= N1; ++i)
            dp[i][0] = i;
        for (int j = 0; j <= N2; ++j)
            dp[0][j] = j;
        for (int i = 1; i <= N1; ++i) {
            for (int j = 1; j <= N2; ++j) {
                char c1 = word1.charAt(i-1);
                char c2 = word2.charAt(j-1);
                if (c1 == c2)
                    dp[i][j] = dp[i-1][j-1];
                else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[N1][N2];
    }
}
