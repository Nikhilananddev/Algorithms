package com.rainz;

/*
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 * Note:
 * All costs are positive integers.
 */
public class PaintHouseII {
    public static void test(String args[]) {
        int[][] input1 = {{1,5,3},{2,9,4}};
        System.out.println(minCostII(input1));
        int[][] input2 = {{8}};
        System.out.println(minCostII(input2));
    }

    /*
     * DP solution: dp[N][K], where N row represents N houses, K column represents *previous* color
     */
    public static int minCostII(int[][] costs) {
        int N = costs.length;
        if (N == 0)
            return 0;
        int K = costs[0].length;
        if (K == 1) {
            if (N == 1)
                return costs[0][0];
            return -1;
        }
        int[][] dp = new int[N][K];
        for (int i = N - 1; i >= 0; --i) {
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            int minColor = -1;
            for (int prevColor = 0; prevColor < K; ++prevColor) {
                int nextCost = i < N - 1 ? dp[i + 1][prevColor] : 0;
                int cost = costs[i][prevColor] + nextCost;
                if (cost < min1) {
                    min2 = min1;
                    min1 = cost;
                    minColor = prevColor;
                } else if (cost < min2)
                    min2 = cost;
            }
            for (int prevColor = 0; prevColor < K; ++prevColor) {
                dp[i][prevColor] = prevColor != minColor ? min1 : min2;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int prevColor = 0; prevColor < K; ++prevColor)
            if (dp[0][prevColor] < ans)
                ans = dp[0][prevColor];
        return ans;
    }
}
