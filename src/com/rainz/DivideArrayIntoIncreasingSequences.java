package com.rainz;

/*
 * Given a non-decreasing array of positive integers nums and an integer K, find out if this array can be divided into one or more disjoint increasing subsequences of length at least K.
 */

public class DivideArrayIntoIncreasingSequences {
    public static void test(String args[]) {
        int[] input1 = {1,2,2,3,3,4,4};
        System.out.println(canDivideIntoSubsequences(input1, 3));
        int[] input2 = {5,6,6,7,8};
        System.out.println(canDivideIntoSubsequences(input2, 3));
    }

    /*
     * Note: this problem doesn't require sequences to be consecutive. They just need to be increasing.
     * Find the most freq #. Let its count be C.
     * In order to use all of them, you need at least C*K #s in nums.
     */
    public static boolean canDivideIntoSubsequences(int[] nums, int K) {
        int mostFreq = 1;
        int count = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i-1]) {
                ++count;
                if (count > mostFreq)
                    mostFreq = count;
            } else
                count = 1;
        }
        return mostFreq*K <= nums.length;
    }
}
