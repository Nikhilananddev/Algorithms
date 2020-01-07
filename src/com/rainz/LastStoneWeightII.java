package com.rainz;

/*
 * We have a collection of rocks, each rock has a positive integer weight.
 * Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)
 */
public class LastStoneWeightII {
    public static void test(String args[]) {
        int[] input1 = {2,7,4,1,8,1};
        System.out.println(lastStoneWeightII(input1));
        int[] input2 = {31,26,33,21,40};
        System.out.println(lastStoneWeightII(input2));
    }

    public static int lastStoneWeightII(int[] stones) {
        int N = stones.length;
        int sum = 0;
        for (int n: stones)
            sum += n;
        int maxWt = sum / 2;
        // Find largest sum of weight <= maxWt
        int[][] dp = new int[N+1][maxWt+1];
        for (int i = 0; i <= N; ++i)
            dp[i][0] = 0;
        for (int i = 0; i <= maxWt; ++i)
            dp[0][i] = 0;
        for (int i = 1; i <= N; ++i) {
            int wt = stones[i-1];
            for (int w = 1; w <= maxWt; ++w) {
                if (wt > w)
                    dp[i][w] = dp[i-1][w];
                else
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-wt]+wt);
            }
        }
        return sum - 2*dp[N][maxWt];
    }

}
