package com.rainz;

/**
 * Created by Yu on 1/25/2015.
 */
public class DivideTwoIntegers {
    public static void test(String args[]) {
        System.out.println(divide(840, 5));
        System.out.println(divide(21, 6));
        System.out.println(divide(3341, 1));
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 0)
            return Integer.MAX_VALUE;
        int sign = dividend < 0 ? -1 : 1;
        sign = divisor < 0 ? -sign : sign;
        long top = Math.abs((long)dividend);
        long bottom = Math.abs((long)divisor);
        long answer = 0;
        while (top >= bottom) {
            long shift = 1;
            long sub = bottom;
            while (sub <= top) {
                sub <<= 1;
                shift <<= 1;
            }
            sub >>= 1;
            shift >>= 1;
            answer += shift;
            top -= sub;
        }
        if (sign < 0)
            answer = -answer;
        if (answer < Integer.MIN_VALUE || answer > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return (int)answer;
    }
}
