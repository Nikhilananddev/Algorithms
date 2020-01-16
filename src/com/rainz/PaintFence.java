package com.rainz;

/*
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * Return the total number of ways you can paint the fence.
 */
public class PaintFence {
    public static void test(String args[]) {
        System.out.println(numWays(3, 2));
    }

    private static int helper(int n, int k, int lastColor, int run, int ans) {
        if (n <= 0)
            return ans+1;
        for (int color = 0; color < k; ++color) {
            if (lastColor == color) {
                if (run >= 2)
                    continue;
                ans = helper(n - 1, k, color, run + 1, ans);
            } else {
                ans = helper(n - 1, k, color, 1, ans);
            }
        }
        return ans;
    }

    public static int numWaysRecursive(int n, int k) {
        if (n == 0 || k == 0)
            return 0;
        return helper(n, k, -1, 0, 0);
    }

    public static int numWays(int n, int k) {
        if (n == 0 || k == 0)
            return 0;
        if (n == 1)
            return k;
        int[] dp = new int[n+1];
        dp[1] = k;
        dp[2] = k*k;
        for (int i = 3; i <= n; ++i)
            dp[i] = (k - 1) * dp[i-1] + (k - 1) * dp[i-2];
        return dp[n];
    }
}
