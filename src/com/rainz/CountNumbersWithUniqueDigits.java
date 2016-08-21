package com.rainz;

/**
 * Created by Yu on 8/21/2016.
 */
public class CountNumbersWithUniqueDigits {
    public static void test(String args[]) {
        System.out.println(countNumbersWithUniqueDigits(3));
    }

    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1; // Note this!!
        }
        // This code should be able to handle n>10.

        int num = 9; // count of numbers without 0
        int prevNum = 0;
        int num0 = 1; // count of numbers with 0
        int result = num + num0;
        for (int digits = 2; digits <= n; ++digits) {
            num0 = num0 * (9 - digits + 2) + prevNum * (9 - digits + 2);
            prevNum = num;
            num = num * (9 - digits + 1);
            result += num + num0;
        }
        return result;
    }
}
