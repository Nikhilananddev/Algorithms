package com.rainz;

public class IntegerReplacement {
    public static void test(String args[]) {
        System.out.println(integerReplacement(8));
        System.out.println(integerReplacement(7));
        System.out.println(integerReplacement(100000000));
        System.out.println(integerReplacement(763));
    }
    /*
     * For 4k+3 where k>0, +1 and -1 takes same # of steps to remove last 3 bits.
     * However, for cases like 763 (0b1011111011), adding 1 connected the new "1" with more "1"s before it.
     * Thus it's more efficient in this case.
     */
    public static int integerReplacement(int n) {
        int steps = 0;
        long num = n; // prevent overflowing for INTEGER.MAX_VALUE
        while (num > 1) {
            if (num % 2 == 0)
                num >>= 1;
            else if ((num & 0x3) == 0x3 && num != 3)
                ++num;
            else
                --num;
            ++steps;
        }
        return steps;
    }
}
