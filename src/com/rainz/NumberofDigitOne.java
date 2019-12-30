package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class NumberofDigitOne {
    public static void test(String args[]) {
        System.out.println(countDigitOne(13));
        System.out.println(countDigitOne(1410065408));
    }

    public static int countDigitOne(int n) {
        int ans = 0;
        int num = n;
        // Count # times 1 appears at each place
        for (long place = 1; num > 0; place *= 10) {
            int digit = num % 10;
            num /= 10;

            long nextPowerOfTen = (place * 10);
            long roundDown = n - (n % nextPowerOfTen);
            long roundUp = roundDown + nextPowerOfTen;

            // Each digit appears 1/10th of the time
            if (digit < 1) {
                ans += roundDown / 10;
            } else if (digit > 1) {
                ans += roundUp / 10;
            } else {
                // The extras are for 10, 11, 12, 13, etc
                ans += roundDown / 10 + (n % place) + 1;
            }
        }

        return ans;
    }
}
