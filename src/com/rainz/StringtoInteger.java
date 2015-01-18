package com.rainz;

/**
 * Created by Yu on 1/18/2015.
 */
public class StringtoInteger {
    public static void test(String args[]) {
        System.out.println(atoi(""));
        System.out.println(atoi(" +123A4"));
        System.out.println(atoi(" -A123"));
        System.out.println(atoi("-0234090"));
        System.out.println(atoi("123"));
        System.out.println(atoi("+123"));
        System.out.println(atoi("-123"));
    }

    public static int atoi(String str) {
        long output = 0;
        int idx = 0;
        int sign = 1;
        // Skip whitespace
        while (idx < str.length() && Character.isWhitespace(str.charAt(idx)))
            ++idx;
        // Check optional sign
        if (idx < str.length()) {
            char c = str.charAt(idx);
            if (c == '-') {
                sign = -1;
                ++idx;
            }
            else if (c == '+') {
                sign = 1;
                ++idx;
            }
        }
        while (idx < str.length()) {
            char c = str.charAt(idx);
            if (c > '9' || c < '0')
                break;
            output = output * 10 + sign * (c - '0');
            if (output > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (output < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            ++idx;
        }
        return (int)output;
    }
}
