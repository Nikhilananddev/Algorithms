package com.rainz;

/*
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 */

public class CoinChange {
    public static void test(String args[]) {
        int[] input1 = {1, 2, 5};
        System.out.println(coinChange(input1, 11));
        int[] input2 = {2};
        System.out.println(coinChange(input2, 3));
    }

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (amount < 0)
            return -1;

        int[] dp = new int[amount+1];
        dp[0] = -1;
        for (int amt = 1; amt <= amount; ++amt) {
            dp[amt] = -1;
            for (int coin: coins) {
                if (amt == coin) {
                    dp[amt] = 1;
                    break;
                }
                if (amt < coin)
                    continue;
                int count = dp[amt - coin];
                if (count < 0)
                    continue;
                ++count; // add this coin
                if (dp[amt] < 0 || dp[amt] > count)
                    dp[amt] = count;
            }
        }
        return dp[amount];
    }
}
