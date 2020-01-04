package com.rainz;

/*
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 * The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
 */

public class CapacityToShipPackagesWithinDDays {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(shipWithinDays(input1, 5));
        int[] input2 = {1,2,3,1,1};
        System.out.println(shipWithinDays(input2, 4));
    }

    public static int shipWithinDays(int[] weights, int D) {
        if (weights.length == 0)
            return 0;
        int maxWeight = Integer.MIN_VALUE;
        int sumWeight = 0;
        for (int w: weights) {
            if (w > maxWeight)
                maxWeight = w;
            sumWeight += w;
        }
        int lower = maxWeight;
        int upper = sumWeight;
        int ans = lower;
        while (lower <= upper) {
            int mid = (lower + upper) / 2;
            int days = 1;
            int capacity = 0;
            for (int w: weights) {
                capacity += w;
                if (capacity > mid) {
                    ++days;
                    capacity = w;
                    if (days > D)
                        break;
                }
            }
            if (days > D)
                lower = mid + 1;
            else {
                ans = mid;
                upper = mid - 1;
            }
        }
        return ans;
    }
}
