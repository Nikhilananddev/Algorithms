package com.rainz;

public class BestTimetoBuyandSellStockII {
    public static void test(String args[]) {
        int[] input1 = {7,1,5,3,6,4};
        System.out.println(maxProfit(input1));
        int[] input2 = {1,2,3,4,5};
        System.out.println(maxProfit(input2));
    }

    public static int maxProfit(int[] prices) {
        int ans = 0;
        int prevLow = Integer.MAX_VALUE;
        for (int p: prices) {
            if (p > prevLow)
                ans += p - prevLow;
            prevLow = p;
        }
        return ans;
    }
}
