package com.rainz;

import java.util.Random;

/*
 * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.
 * Note:
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10000 times.
 */
public class RandomPickwithWeight {
    public static void test(String args[]) {
        int[] input1 = {1, 3};
        RandomPickwithWeight pick = new RandomPickwithWeight(input1);
        for (int i = 0; i < 10; ++i)
            System.out.println(pick.pickIndex());
    }

    // Prefix sum + binary search

    private int[] prefixSum;
    private Random rand = new Random();
    public RandomPickwithWeight(int[] w) {
        prefixSum = new int[w.length];
        for (int i = 0 ; i < w.length; ++i)
            prefixSum[i] = w[i] + (i > 0 ? prefixSum[i-1] : 0);
    }

    public int pickIndex() {
        int total = prefixSum[prefixSum.length-1];
        int rnd = rand.nextInt(total) + 1;
        int lo = 0, hi = prefixSum.length-1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (rnd > prefixSum[mid])
                lo = mid + 1;
            else
                hi = mid;
        }
        //System.out.println("rand="+rnd+", hi="+hi);
        return hi;
    }

}
