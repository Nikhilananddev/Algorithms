package com.rainz;

/**
 * Created by Yu on 2/7/2015.
 */
public class Pow {
    public static void test(String args[]) {
        System.out.println(pow(2, 3));
        System.out.println(pow(2, 4));
        System.out.println(pow(2, -3));
        System.out.println(pow(2, -4));
        System.out.println(pow(8.84372, -5));
    }

    public static double pow(double x, int n) {
        if (n == 0)
            return 1;
        if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                // n = -n will cause overflow for MIN_VALUE, so handle it here.
                return 1 / pow(x, -(n+1)) / x;
            }
            return 1 / pow(x, -n);
        }

        double tmp = pow(x, n/2);
        double answer = tmp * tmp;
        return (n % 2 == 1 ? answer*x : answer);
    }
}
