package com.rainz;

/*
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 */

public class UniqueBinarySearchTrees {
    public static void test(String args[]) {
        System.out.println(numTrees(3));
        System.out.println(numTrees(4));
    }

    public static int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            dp[i] = 0;
            for (int num = 1; num <= i; ++num) {
                int left = i - num;
                int right = i - left - 1;
                dp[i] += dp[left]*dp[right];
            }
        }
        return dp[n];
    }
}
