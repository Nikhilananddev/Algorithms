package com.rainz;

public class LongestCommonSubsequence {
    public static void test(String args[]) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));
        System.out.println(longestCommonSubsequence("abc", "abc"));
        System.out.println(longestCommonSubsequence("abc", "def"));
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int N1 = text1.length(), N2 = text2.length();
        int[][] dp = new int[N1+1][N2+1];
        for (int i = 0; i <= N1; ++i)
            dp[i][0] = 0;
        for (int j = 0; j <= N2; ++j)
            dp[0][j] = 0;
        for (int i = 1; i <= N1; ++i) {
            for (int j = 1; j <= N2; ++j) {
                char c1 = text1.charAt(i-1);
                char c2 = text2.charAt(j-1);
                if (c1 == c2)
                    dp[i][j] = 1 + dp[i-1][j-1];
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[N1][N2];
    }
}
