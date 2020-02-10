package com.rainz;

/*
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 */
public class PalindromePartitioningII {
    public static void test(String args[]) {
        System.out.println(minCut("aab"));
    }

    public static int minCut(String s) {
        int L = s.length();
        // First, build isPal[][] to store whether substring [i,j] is a palindrome
        boolean[][] isPal = new boolean[L][L];
        for (int len = 1; len <= L; ++len) {
            for (int i = 0; i + len <= L; ++i) {
                int j = i + len - 1;
                isPal[i][j] = (s.charAt(i) == s.charAt(j) && (j-i <= 2 || isPal[i+1][j-1]));
            }
        }
        // Now for each substring [0,i],  find each j in between such that [j,i] is pal, and record min cut.
        int[] dp = new int[L];
        for (int i = 1; i < L; ++i) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j <= i; ++j) {
                if (isPal[j][i]) {
                    int cut = j > 0 ? dp[j-1] + 1 : 0;
                    if (cut < min)
                        min = cut;
                }
            }
            dp[i] = min;
        }
        return dp[L-1];
    }
}
