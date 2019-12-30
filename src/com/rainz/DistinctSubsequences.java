package com.rainz;

/*
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 */

public class DistinctSubsequences {
    public static void test(String args[]) {
        System.out.println(numDistinct("rabbbit", "rabbit"));
        System.out.println(numDistinct("babgbag", "bag"));
    }

    public static int numDistinct(String s, String t) {
        int lenS = s.length(), lenT = t.length();
        int dp[][] = new int[lenS+1][lenT+1];

        for (int i = 0; i <= lenS; ++i)
            dp[i][lenT] = 1;
        for (int i = 0; i <= lenT-1; ++i)
            dp[lenS][i] = 0;

        for (int idxS = lenS-1; idxS >= 0; --idxS) {
            for (int idxT = lenT-1; idxT >= 0; --idxT) {
                char charS = s.charAt(idxS);
                char charT = t.charAt(idxT);
                dp[idxS][idxT] = dp[idxS+1][idxT];
                if (charS == charT)
                    dp[idxS][idxT] += dp[idxS+1][idxT+1];
            }
        }

        return dp[0][0];
    }
}
