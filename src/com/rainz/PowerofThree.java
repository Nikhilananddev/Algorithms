package com.rainz;

public class PowerofThree {

    public static void test(String args[]) {
        System.out.println(isPowerOfThree(27));
        System.out.println(isPowerOfThree(0));
        System.out.println(isPowerOfThree(1));
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(45));
    }

    /*
     * 3^19 = 1162261467 is the biggest power of 3 less than Integer.MAX_VALUE.
     * Since 3 is a prime number, the only divisors of 3^19 are all powers of 3, aka 3^0, 3^1, 3^2, ... 3^19
     * So n is a power of 3 if and only if n > 0 and 1162261467 is divisible by n
     */

    public static boolean isPowerOfThree(int n) {
        return n > 0 &&
                1162261467 % n == 0;
    }
}
