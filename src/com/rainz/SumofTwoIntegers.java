package com.rainz;

/*
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 */

public class SumofTwoIntegers {
    public static void test(String args[]) {
        System.out.println(getSum(1, 2));
        System.out.println(getSum(-2, 3));
        System.out.println(getSum(4, 5));
    }

    /*
     * "^" is addition without carry. "&" gets you carry bits since 1 & 1 = 1.
     * So we do both to get sum without carry and the carry bits.
     * Then we attempt to add carry to sum by repeating steps above until carry is 0
     */
    public static int getSum(int a, int b) {
        do {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        } while (b != 0);
        return a;
    }
}
