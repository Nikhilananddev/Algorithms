package com.rainz;

public class PowerofFour {
    public static void test(String args[]) {
        System.out.println(isPowerOfFour(1));
        System.out.println(isPowerOfFour(2));
        System.out.println(isPowerOfFour(3));
        System.out.println(isPowerOfFour(4));
        System.out.println(isPowerOfFour(5));
        System.out.println(isPowerOfFour(8));
        System.out.println(isPowerOfFour(16));
        System.out.println(isPowerOfFour(32));
        System.out.println(isPowerOfFour(218));
    }

    public static boolean isPowerOfFour(int num) {
        return (num > 0 && (num & (num-1)) == 0 && (num & 0x55555555) > 0);
    }
}
