package com.rainz;

public class NthDigit {
    public static void test(String args[]) {
        System.out.println(nthDigit(3));
        System.out.println(nthDigit(11));
        System.out.println(nthDigit(1000000000)); // expect 1
    }

    public static int nthDigit(int n) {
        long base = 1; // 1, 10, 100, ...
        int numLen = 1; // digits per number
        // lenStart, lenEnd map to begin & end of 10..0 and 99..9
        long lenStart = 0;
        long lenEnd = 0;

        while (true) {
            lenStart = lenEnd + 1;
            lenEnd = lenStart + numLen*base*9 - 1;
            if (n <= lenEnd)
                break;
            base *= 10;
            ++numLen;
        }
        int num = (int)(base + (n - lenStart) / numLen);
        int digit = (int)((n - lenStart) % numLen);
        char c = String.valueOf(num).charAt(digit);
        return c-'0';
    }
}
