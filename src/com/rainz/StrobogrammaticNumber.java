package com.rainz;

/*
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 */
public class StrobogrammaticNumber {
    public static void test(String args[]) {
        System.out.println(isStrobogrammatic("69"));
        System.out.println(isStrobogrammatic("88"));
        System.out.println(isStrobogrammatic("962"));
    }

    public static boolean isStrobogrammatic(String num) {
        final char[] strobo = {'0', '1', 'x', 'x', 'x', 'x', '9', 'x', '8', '6'};
        int start = 0, end = num.length()-1;
        while (start <= end) {
            if (num.charAt(start) != strobo[num.charAt(end)-'0'])
                return false;
            ++start;
            --end;
        }
        return true;
    }

}
