package com.rainz;

/**
 * Created by Yu on 2/22/2015.
 */
public class ClimbingStairs {
    public static void test(String args[]) {
        for (int n = 0; n < 10; ++n)
            System.out.println(""+n+": "+climbStairs(n));
    }
    public static int climbStairs(int n) {
        if (n < 2)
            return 1;
        int a = 1, b = 1, result = 0;
        int val = 2;
        while (val <= n) {
            result = a + b;
            a = b;
            b = result;
            ++val;
        }
        return result;
    }
}
