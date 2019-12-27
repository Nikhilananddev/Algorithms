package com.rainz;
/*
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 * Return the maximum profit you can make.
 */
public class BestTimetoBuyandSellStockwithTransactionFee {
    public static void test(String args[]) {
        int[] input1 = {1,3,2,8,4,9};
        System.out.println(maxProfit(input1, 2));
        int[] input2 = {1,3,7,5,10,3};
        System.out.println(maxProfit(input2, 3));
        int[] input3 = {4,5,2,4,3,3,1,2,5,4};
        System.out.println(maxProfit(input3, 1));
    }

    public static int maxProfit(int[] prices, int fee) {
        int disown = 0, own = -prices[0];
        // own and disown represent the money you have each day if you own or not own this stock.
        for (int price : prices) {
            int disownOld = disown;
            // disown: either I didn't own it yesterday, or I just sold it today
            disown = Math.max(disown, own + price - fee);
            // own: either I own it yesterday, or I just bought it today
            own = Math.max(own, disownOld - price);
        }
        // At the end I don't own the stock
        return disown;
    }
}
