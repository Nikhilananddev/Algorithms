package com.rainz;

/*
 * Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
 */
public class MinimumFlipstoMakeaORbEqualtoc {
    public static void test(String args[]) {
        System.out.println(minFlips(2, 6, 5));
        System.out.println(minFlips(4, 2, 7));
        System.out.println(minFlips(1, 2, 3));
        System.out.println(minFlips(8, 3, 5));
    }
    public static int minFlips(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int shift = 1 << i;
            int aBit = a & shift;
            int bBit = b & shift;
            int cBit = c & shift;
            if ((aBit | bBit) == cBit)
                continue;
            if (cBit == 0) {
                if (aBit != 0)
                    ++ans;
                if (bBit != 0)
                    ++ans;
            } else {
                ++ans;
            }
        }
        return ans;
    }
}
