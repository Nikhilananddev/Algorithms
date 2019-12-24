package com.rainz;

public class PowerofTwo {
    public static void test(String args[]) {
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(218));
    }

    public static boolean isPowerOfTwo(int n) {
        return (n > 0 && (n & (n-1)) == 0);
    }
}
