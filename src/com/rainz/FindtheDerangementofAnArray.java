package com.rainz;

/*
 * In combinatorial mathematics, a derangement is a permutation of the elements of a set, such that no element appears in its original position.
 * There's originally an array consisting of n integers from 1 to n in ascending order, you need to find the number of derangement it can generate.
 * Also, since the answer may be very large, you should return the output mod 109 + 7.
 */
public class FindtheDerangementofAnArray {
    public static void test(String args[]) {
        System.out.println(findDerangement(3));
        System.out.println(findDerangement(13));
    }

    public static int findDerangement(int n) {
        if (n < 3)
            return n-1;
        final int MODULO = 1000000007;
        long dp1 = 0, dp2 = 1;
        for (int i = 3; i <= n; ++i) {
            long dp = (i-1)*(dp2 + dp1) % MODULO;
            dp1 = dp2;
            dp2 = dp;
        }
        return (int)dp2;
    }
}
