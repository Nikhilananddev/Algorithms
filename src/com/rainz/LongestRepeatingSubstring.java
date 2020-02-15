package com.rainz;

/*
 * Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.
 */
public class LongestRepeatingSubstring {
    public static void test(String args[]) {
        System.out.println(longestRepeatingSubstring("abcd"));
        System.out.println(longestRepeatingSubstring("abbaba"));
        System.out.println(longestRepeatingSubstring("aabcaabdaab"));
        System.out.println(longestRepeatingSubstring("aaaaa"));
    }

    // DP solution. This can also be solved by using a HashMap/HashSet of Strings and start from longest
    public static int longestRepeatingSubstring(String S) {
        int L = S.length();
        if (L == 0)
            return 0;
        int ans = 0;
        int[][] dp = new int[L+1][L+1]; // [end1][end2];
        for (int i = 0; i < L; ++i) {
            for (int j = i + 1; j < L; ++j) {
                if (S.charAt(i) == S.charAt(j)) {
                    int prev = i > 0 ? dp[i-1][j-1] : 0;
                    dp[i][j] = 1 + prev;
                    ans = Math.max(dp[i][j], ans);
                }
            }
        }
        return ans;
    }
}
