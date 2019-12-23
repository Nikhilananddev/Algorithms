package com.rainz;

public class BestTimetoBuyandSellStock {
    public static void test(String args[]) {
        int[] input1 = {7,1,5,3,6,4};
        System.out.println(maxProfit(input1));
        int[] input2 = {7,6,4,3,1};
        System.out.println(maxProfit(input2));
    }

    public static int maxProfit(int[] prices) {
        int result = 0;
        int minSoFar = Integer.MAX_VALUE;
        for (int p: prices) {
            if (p < minSoFar)
                minSoFar = p;
            else {
                int profit = p - minSoFar;
                if (profit > result)
                    result = profit;
            }
        }
        return result;
    }
}
