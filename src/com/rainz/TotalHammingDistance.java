package com.rainz;

/*
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Now your job is to find the total Hamming distance between all pairs of the given numbers.
 */

public class TotalHammingDistance {
    public static void test(String args[]) {
        int[] input1 = {4,14,2};
        System.out.println(totalHammingDistance(input1));
    }

    public static int totalHammingDistance(int[] nums) {
        int ans = 0;
        int[] ones = new int[32];
        for (int n: nums) {
            for (int shift = 0; shift < 32; ++shift) {
                if ( (n & (1 << shift)) != 0 )
                    ++ones[shift];
            }
        }
        for (int i = 0; i < ones.length; ++i) {
            ans += ones[i] * (nums.length - ones[i]);
        }
        return ans;
    }
}
