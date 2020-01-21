package com.rainz;

import javax.swing.text.html.ListView;

/*
 * Given a string s and an integer k, find out if the given string is a K-Palindrome or not.
 * A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 */
public class ValidPalindromeIII {
    public static void test(String args[]) {
        System.out.println(isValidPalindrome("abcdeca", 2));
    }

    // Basically longest palindromic subsequence
    public static boolean isValidPalindrome(String s, int k) {
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
        return s.length() - dp[0][s.length()] <= k;
    }
}
