package com.rainz;

/**
 * Created by Yu on 2/22/2015.
 */
public class Sqrt {
    public static void test(String args[]) {
        for (int x = 0; x <= 25; ++x) {
            System.out.println(""+x+": "+sqrt(x));
        }
    }

    public static  int sqrt(int x) {
        int lower = 0, upper = x;
        while (lower < upper - 1) {
            int mid = lower + (upper - lower) / 2;
            if ((double)mid*mid > x) {
                upper = mid;
            } else {
                lower = mid;
            }
        }
        // Now diff between upper and lower is at most 1, so just pick one
        return ((double)upper*upper > x) ? lower : upper;
    }
}
