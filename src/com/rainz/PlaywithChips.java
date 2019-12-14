package com.rainz;

public class PlaywithChips {
    public static void test(String args[]) {
        int[] input1 = {1,2,3};
        int[] input2 = {2,2,2,3,3};
        System.out.println(minCostToMoveChips((input1)));
        System.out.println(minCostToMoveChips((input2)));
    }

    public static int minCostToMoveChips(int[] chips) {
        /* Since move 2 units is free, we move all evens to 2,
         * and move all odds to 1. Then move chips in the smaller pile into bigger pile.
         * This will incur a cost of 1 per chip.
         */
        int evenCount = 0;
        int oddCount = 0;
        for (int x: chips) {
            if (x % 2 == 0)
                ++evenCount;
            else
                ++oddCount;
        }
        if (evenCount > oddCount)
            return oddCount;
        else
            return evenCount;
    }
}
