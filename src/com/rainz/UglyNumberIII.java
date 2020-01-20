package com.rainz;

/*
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive integers which are divisible by a or b or c.
 */

public class UglyNumberIII {
    public static void test(String args[]) {
//        System.out.println(nthUglyNumber(3, 2, 3, 5));
//        System.out.println(nthUglyNumber(4, 2, 3, 4));
//        System.out.println(nthUglyNumber(5, 2, 11, 13));
//        System.out.println(nthUglyNumber(1000000000, 2, 217983653, 336916467));
        System.out.println(nthUglyNumber(14, 3, 7, 13));
    }

    private static long lcm(long a, long b) {
        // First find gcd of a & b
        long prod = a * b;
        while (b > 0) {
            // Works even if b > a
            long r = a % b;
            a = b;
            b = r;
        }
        return prod/a; // lcm = prod / gcd
    }

    // Binary searching using inclusion-exclusion principle
    public static int nthUglyNumber(int n, int a, int b, int c) {
        int minAbc = Math.min(a, Math.min(b, c));
        long ab = lcm(a, b), bc = lcm(b, c), ac = lcm(a, c), abc = lcm(a, bc);
        long lo = 1, hi = n*minAbc;
        while (lo <= hi) {
            long mid = lo + (hi - lo)/2;
            long count = mid/a + mid/b + mid/c - mid/ab - mid/bc - mid/ac + mid/abc;
            if (count == n) {
                // Must check if mid actually divisible by a, b, or c!!!!!!!!!!!
                // mid could be a number after real answer
                if (mid % a == 0 || mid % b == 0 || mid % c == 0)
                    return (int) mid; // found
                hi = mid - 1; // answer is close, but before mid
            } else if (count < n)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return -1;
    }
}
