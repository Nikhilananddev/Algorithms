package com.rainz;

/**
 * Created by Yu on 2/22/2015.
 */
public class EditDistance {
    public static void test(String args[]) {
        System.out.println(minDistance("ABCD", "ABDE"));
        System.out.println(minDistance("ABC", "BCE"));
        System.out.println(minDistance("", "BCE"));
        System.out.println(minDistance("ASDFB", ""));
        System.out.println(minDistance("ACDE", "ABCDEF"));
        System.out.println(minDistance("", ""));
    }

    protected static int minDistRecursive(String word1, int idx1, String word2, int idx2) {
        if (idx1 == word1.length())
            return word2.length() - idx2;
        if (idx2 == word2.length())
            return word1.length() - idx1;
        if (word1.charAt(idx1) == word2.charAt(idx2))
            return minDistRecursive(word1, idx1+1, word2, idx2+1);
        int replace = minDistRecursive(word1, idx1+1, word2, idx2+1);
        int add = minDistRecursive(word1, idx1+1, word2, idx2);
        int minDist = Math.min(replace, add);
        int remove = minDistRecursive(word1, idx1, word2, idx2+1);
        minDist = Math.min(minDist, remove);
        return 1 + minDist;
    }

    public static int minDistance(String word1, String word2) {
        //return minDistRecursive(word1, 0, word2, 0);
        int rows = word1.length()+1, cols = word2.length()+1;
        int [][]dp = new int[rows][cols];
        for (int r = 0; r < rows; ++r)
            dp[r][cols-1] = rows - 1 - r;
        for (int c = 0; c < cols - 1; ++c)
            dp[rows-1][c] = cols - 1 - c;
        for (int r = rows-2; r >= 0; --r) {
            for (int c = cols - 2; c >= 0; --c) {
                if (word1.charAt(r) == word2.charAt(c)) {
                    dp[r][c] = dp[r+1][c+1];
                    continue;
                }
                int minDist = Math.min(dp[r+1][c], dp[r][c+1]);
                minDist = Math.min(minDist, dp[r+1][c+1]);
                dp[r][c] = 1 + minDist;
            }
        }
        return dp[0][0];
    }
}
