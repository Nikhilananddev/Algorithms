package com.rainz;

/*
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
 */

public class MinCostClimbingStairs {
    public static void test(String args[]) {
        int[] input1 = {10, 15, 20};
        System.out.println(minCostClimbingStairs(input1));
        int[] input2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(input2));
    }

    private static int helper(int[] cost, int start) {
        if (start >= cost.length)
            return 0;
        int curr = start < 0 ? 0 : cost[start];
        int cost1 = helper(cost, start+1);
        int cost2 = helper(cost, start+2);
        return curr + Math.min(cost1, cost2);
    }

    public static int minCostClimbingStairsRecursive(int[] cost) {
        return helper(cost, -1);
    }

    public static int minCostClimbingStairs(int[] cost) {
        int N = cost.length;
        // Note: in this problem, reaching the top means reaching 0-based index N
        int[] dp = new int[N+1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= N; ++i)
            dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
        return dp[N];
    }
}
