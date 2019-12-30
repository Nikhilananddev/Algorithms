package com.rainz;

public class CoinChange2 {
    public static void test(String args[]) {
        int[] input1 = {1, 2, 5};
        System.out.println(change(5, input1));
        int[] input2 = {2};
        System.out.println(change(3, input2));
    }

    public static int change(int amount, int[] coins) {
        if (coins.length == 0)
            return amount == 0 ? 1 : 0;

        int[][] dp = new int[amount+1][coins.length];
        int coinIdx = coins.length - 1;
        int coin = coins[coinIdx];
        for (int amt = 0; amt < amount+1; ++amt)
            dp[amt][coinIdx] = amt % coin == 0 ? 1 : 0;
        --coinIdx;
        for (; coinIdx >= 0; --coinIdx) {
            coin = coins[coinIdx];
            for (int amt = 0; amt < amount+1; ++amt) {
                dp[amt][coinIdx] = 0;
                int remainAmt = amt;
                while (remainAmt >= 0) {
                    dp[amt][coinIdx] += dp[remainAmt][coinIdx + 1];
                    remainAmt -= coin;
                }
            }
        }

        return dp[amount][0];
    }

}
