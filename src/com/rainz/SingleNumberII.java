package com.rainz;

/*
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 */
public class SingleNumberII {
    public static void test(String args[]) {
        int[] input1 = {2,2,3,2};
        System.out.println(singleNumber(input1));
        int[] input2 = {0,1,0,1,0,1,99};
        System.out.println(singleNumber(input2));
    }

    /*
     * For each of the 32 bits, count # appearances of 1's. Bits with 1 appearing 3k+1 times are part of answer.
     * Use 3 masks. "one": 1 means on this bit, 1 appeared 3k+1 times; "two": 3k+2 times; "three": 3k times.
     */

    public static int singleNumber(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < nums.length; i++) {
            twos |= ones & nums[i]; // if this bit is 1 and prev one bit is 1 (aka 3k+1), then now it's 3k+2
            ones ^= nums[i]; // if this bit is 1, one gets toggle between odd & even, so either 3k+1 or 3k+3 (aka 3k)
            threes = ones & twos; // if both two and one are on, we have a 3k
            ones &= ~threes; // clear one if three is on
            twos &= ~threes; // clear two if three is on
        }
        return ones;
    }
}
