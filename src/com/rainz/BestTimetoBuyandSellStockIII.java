package com.rainz;

/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 */

public class BestTimetoBuyandSellStockIII {
    public static void test(String args[]) {
        int[] input1 = {3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(input1));
        int[] input2 = {1,2,3,4,5};
        System.out.println(maxProfit(input2));
        int[] input3 = {3,2,6,5,0,3};
        System.out.println(maxProfit(input3));
    }

    public static int maxProfit(int[] prices) {
        int N = prices.length;

        // First, find and store max profit for all [0, i]
        int minSoFar = Integer.MAX_VALUE;
        int[] profits1 = new int[N];
        for (int i = 0; i < N; ++i) {
            int p = prices[i];
            if (p < minSoFar)
                minSoFar = p;
            else
                profits1[i] = p - minSoFar;
        }
        // Second, starting from right side, find max profit for all [i, N-1]...
        // ...and store max of max profit for each of them
        int maxSoFar = 0;
        int[] maxProfits2 = new int[N];
        for (int i = N-1; i >= 0; --i) {
            int p = prices[i];
            int profit = 0;
            if (p > maxSoFar)
                maxSoFar = p;
            else
                profit = maxSoFar - p;
            if (i == N-1 || profit > maxProfits2[i+1])
                maxProfits2[i] = profit;
            else
                maxProfits2[i] = maxProfits2[i+1];

        }
        // Finally, find max profit for each day "i"...
        // ... by adding profit for "i" from left to max profit from right for "i+1"
        int result = 0;
        for (int i = 0; i < N; ++i) {
            int p1 = profits1[i];
            int p2 = i < N-1 ? maxProfits2[i+1] : 0;
            if (p1 + p2 > result)
                result = p1 + p2;
        }
        return result;
    }
}
