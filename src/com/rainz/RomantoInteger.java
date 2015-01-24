package com.rainz;

/**
 * Created by Yu on 1/24/2015.
 */
public class RomantoInteger {
    public static void test(String args[]) {
        System.out.println(romanToInt("I"));
        System.out.println(romanToInt("VII"));
        System.out.println(romanToInt("XIV"));
        System.out.println(romanToInt("MMMXC"));
    }

    public static int romanToInt(String s) {
        int[] roman= new int[256];
        roman['I'] = 1;
        roman['V'] = 5;
        roman['X'] = 10;
        roman['L'] = 50;
        roman['C'] = 100;
        roman['D'] = 500;
        roman['M'] = 1000;

        int result = 0;
        int prev = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            int val = roman[c];
            result += val;
            if (val > prev) {
                result -= 2*prev;
            }
            prev = val;
        }

        return result;
    }
}
