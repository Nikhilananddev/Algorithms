package com.rainz;

/*
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 */
public class LongestPalindromicSubsequence {
    public static void test(String args[]) {
        System.out.println(longestPalindromeSubseq("bbbab"));
        System.out.println(longestPalindromeSubseq("cbbd"));
    }

    public static int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()+1]; // dp[startIdx][length]
        for (int i = 0; i < s.length(); ++i)
            dp[i][1] = 1;
        // Must put l as the outer loop! If i is out loop, we would have i+1 not computed!!
        for (int l = 2; l <= s.length(); ++l) {
            for (int i = 0; i+l <= s.length(); ++i) {
                if (s.charAt(i) == s.charAt(i+l-1))
                    dp[i][l] = 2 + dp[i+1][l-2];
                else
                    dp[i][l] = Math.max(dp[i][l-1], dp[i+1][l-1]);
            }
        }
        return dp[0][s.length()];
    }
}
