package com.rainz;

/*
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 */
public class PaintHouse {
    public static void test(String args[]) {
        int[][] input1 = {{17,2,17},{16,16,5},{14,3,19}};
        System.out.println(minCost(input1));
    }

    private static int dfs(int[][] costs, int house, int lastColor, int cost, int ans) {
        if (house >= costs.length) {
            if (cost < ans)
                ans = cost;
            return ans;
        }
        if (cost > ans)
            return ans;
        for (int i = 0; i < costs[house].length; ++i) {
            if (i != lastColor) {
                ans = dfs(costs, house+1, i, cost+costs[house][i], ans);
            }
        }
        return ans;
    }

    public static int minCostRecursive(int[][] costs) {
        return dfs(costs, 0, -1, 0, Integer.MAX_VALUE);
    }

    public static int minCost(int[][] costs) {
        int H = costs.length;
        if (H == 0)
            return 0;
        int[][] dp = new int[H][3];
        for (int i = 0; i < 3; ++i)
            dp[0][i] = costs[0][i];
        for (int h = 1; h < H; ++h) {
            dp[h][0] = Math.min(dp[h-1][1], dp[h-1][2]) + costs[h][0];
            dp[h][1] = Math.min(dp[h-1][2], dp[h-1][0]) + costs[h][1];
            dp[h][2] = Math.min(dp[h-1][0], dp[h-1][1]) + costs[h][2];
        }
        int ans = Math.min(dp[H-1][0], dp[H-1][1]);
        ans = Math.min(ans, dp[H-1][2]);
        return ans;
    }
}
