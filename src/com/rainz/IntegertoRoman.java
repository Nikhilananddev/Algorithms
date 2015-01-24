package com.rainz;

import java.util.HashMap;

/**
 * Created by Yu on 1/22/2015.
 */
public class IntegertoRoman {
    public static void test(String args[]) {
        System.out.println(intToRoman(1));
        System.out.println(intToRoman(102));
        System.out.println(intToRoman(8));
        System.out.println(intToRoman(54));
        System.out.println(intToRoman(3999));
        System.out.println(intToRoman(10));
        System.out.println(intToRoman(110));
        System.out.println(intToRoman(500));
    }

    public static String intToRoman(int num) {
        char[] ones = {'M', 'C', 'X', 'I'};
        char[] fives = {'?', 'D', 'L', 'V'};

        StringBuilder result = new StringBuilder();
        int baseIdx = 0;
        for (int base = 1000;num > 0; base /= 10, ++baseIdx) {
            int digit = num / base;
            num %= base;
            if (digit == 9) {
                result.append(ones[baseIdx]);
                result.append(ones[baseIdx-1]);
            } else if (digit == 4) {
                result.append(ones[baseIdx]);
                result.append(fives[baseIdx]);
            } else if (digit == 5) {
                result.append(fives[baseIdx]);
            } else {
                if (digit > 5) {
                    digit -= 5;
                    result.append(fives[baseIdx]);
                }
                while (digit > 0) {
                    result.append(ones[baseIdx]);
                    --digit;
                }
            }
        }

        return result.toString();
    }
}
