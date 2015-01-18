package com.rainz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu on 1/18/2015.
 */
public class ReverseInteger {
    public static void test(String args[]) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(12300));
        System.out.println(reverse(-10203));
    }

    public static int reverse(int x) {
        int sign = x > 0 ? 1 : -1;
        x = Math.abs(x);
        List<Integer> digits = new ArrayList<Integer>();
        while (x > 0) {
            digits.add(x % 10);
            x /= 10;
        }
        long output = 0;
        for (int d: digits) {
            output = output * 10 + d*sign;
            if (output > Integer.MAX_VALUE || output < Integer.MIN_VALUE)
                return 0;
        }
        return (int)output;
    }
}
