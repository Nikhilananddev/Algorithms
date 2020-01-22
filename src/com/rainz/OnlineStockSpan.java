package com.rainz;

import java.util.Stack;

/*
 * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.
 * For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].
 */
public class OnlineStockSpan {
    public static void test(String args[]) {
        OnlineStockSpan S = new OnlineStockSpan();
        int[] inputs = {100, 80, 60, 70, 60, 75, 85};
        for (int n: inputs)
            System.out.println(S.next(n));
    }

    private Stack<int[]> stk = new Stack<>(); // {price, day}
    private int day = 0;

    public OnlineStockSpan() {

    }

    public int next(int price) {
        ++day;
        while (!stk.isEmpty() && stk.peek()[0] <= price)
            stk.pop();
        int prevDay = stk.isEmpty() ? 0 : stk.peek()[1];
        int[] curr = {price, day};
        stk.push(curr);
        return day - prevDay;
    }
}
