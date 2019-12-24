package com.rainz;

/*
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 */
public class IntegerBreak {
    public static void test(String args[]) {
        System.out.println(integerBreak(2));
        System.out.println(integerBreak(10));
    }

    public static int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            int max = 0;
            for (int j = 1; j < i; ++j) {
                // Note that product of splitting might be smaller than self
                int product2 = j*(i-j);
                int productN = j*dp[i-j];
                int product = product2 > productN ? product2 : productN;
                if (product > max)
                    max  = product;
            }
            dp[i] = max;
        }
        return dp[n];
    }
}
