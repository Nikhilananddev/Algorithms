package com.rainz;

/*
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 */

public class SingleNumberIII {
    public static void test(String args[]) {
        int[] input1 = {1,2,1,3,2,5};
        Main.printArray(singleNumber(input1));
    }

    /*
     * Xor'ing every number will get you xor result of the two numbers
     * Since these two numbers are different, we can find at least one 1-bit in xor result.
     * We then put all numbers into two groups based on this bit.
     * Note that if a number appears twice in original array, they will both be in the same group.
     * Then xor all numbers in one of the groups to get one of the two numbers.
     * The other can be computed by xor'ing first number with the xor value of the entire array.
     */
    public static int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int n: nums)
            xor ^= n;
        int shift;
        for (shift = 0; shift < 32; ++shift)
            if ((xor & (1 << shift)) != 0)
                break;

        int xor1 = 0;
        for (int n: nums) {
            if ((n & (1 << shift)) != 0)
                xor1 ^= n;
        }
        int[] ans = {xor1, xor1^xor};
        return ans;
    }
}
