package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).
 * The returned string must have no leading zeroes, unless the string is "0".
 */
public class ConverttoBaseMinus2 {
    public static void test(String args[]) {
        System.out.println(baseNeg2(2));
        System.out.println(baseNeg2(3));
        System.out.println(baseNeg2(4));
    }

    public static String baseNeg2(int N) {
        if (N == 0)
            return "0";
        List<Character> digits = new ArrayList<>();
        while (N != 0) { // note it's != 0, not > 0
            int d = N % (-2); // d < 0 if N < 0
            // Note the part below
            if (d < 0) {
                d += 2;
                N -= 2;
            }
            digits.add((char)(d + '0'));
            N /= -2;
        }
        StringBuilder sb = new StringBuilder();
        for (char c: digits)
            sb.append(c);
        sb.reverse();
        return sb.toString();
    }

}
