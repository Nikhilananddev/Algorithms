package com.rainz;

public class KokoEatingBananas {
    public static void test(String args[]) {
        int[] input1 = {3,6,7,11};
        System.out.println(minEatingSpeed(input1, 8));
        int[] input2 = {30,11,23,4,20};
        System.out.println(minEatingSpeed(input2, 5));
        int[] input3 = {30,11,23,4,20};
        System.out.println(minEatingSpeed(input3, 6));
    }

    public static int minEatingSpeed(int[] piles, int H) {
        if (piles.length == 0)
            return 0;
        int lower = 1;
        int upper = 1;
        for (int p: piles)
            if (p > upper)
                upper = p;
        int ans = Integer.MAX_VALUE;
        while (lower <= upper) {
            int K = (lower + upper) / 2;
            int hours = 0;
            for (int p: piles) {
                hours += p / K;
                if (p % K > 0)
                    ++hours;
            }
            if (hours <= H) {
                if (K < ans)
                    ans = K;
                upper = K - 1;
            } else {
                lower = K + 1;
            }
        }
        return ans;
    }
}
