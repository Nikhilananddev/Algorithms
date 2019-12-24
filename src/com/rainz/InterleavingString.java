package com.rainz;

public class InterleavingString {
    public static void test(String args[]) {
        System.out.println(isInterleave("aabcc","dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc","dbbca", "aadbbbaccc"));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;
        for (int len1 = 1; len1 <= s1.length(); ++len1) {
            dp[len1][0] = (dp[len1-1][0] && s1.charAt(len1-1) == s3.charAt(len1-1));
        }
        for (int len2 = 1; len2 <= s2.length(); ++len2) {
            dp[0][len2] = (dp[0][len2-1] && s2.charAt(len2-1) == s3.charAt(len2-1));
        }
        for (int len1 = 1; len1 <= s1.length(); ++len1) {
            for (int len2 = 1; len2 <= s2.length(); ++len2) {
                char c1 = s1.charAt(len1-1);
                char c2 = s2.charAt(len2-1);
                char c3 = s3.charAt(len1+len2-1);
                if (c3 == c1 && c3 == c2) {
                    dp[len1][len2] = (dp[len1-1][len2] || dp[len1][len2-1]);
                } else if (c3 == c1) {
                    dp[len1][len2] = dp[len1-1][len2];
                } else if (c3 == c2) {
                    dp[len1][len2] = dp[len1][len2-1];
                } else {
                    continue; // char doesn't match, false.
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
