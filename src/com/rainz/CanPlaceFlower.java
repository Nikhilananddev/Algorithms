package com.rainz;

public class CanPlaceFlower {
    public static void test(String args[]) {
        int[] input = {1,0,0,0,1};
        System.out.println(canPlaceFlowers(input, 1));
        System.out.println(canPlaceFlowers(input, 2));
        int[] input2 = {1,0,0,0,0,1};
        System.out.println(canPlaceFlowers(input2, 2));
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        // Just use greedy strategy.
        for (int idx = 0; n > 0 && idx < flowerbed.length; ++idx) {
            if (flowerbed[idx] != 0)
                continue;
            if ((idx > 0 && flowerbed[idx-1] != 0) ||
                    (idx < flowerbed.length-1 && flowerbed[idx+1] != 0))
                continue;
            flowerbed[idx] = 1;
            --n;
        }
        return (n == 0);
    }
}
