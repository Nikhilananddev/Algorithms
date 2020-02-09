package com.rainz;

/*
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 */

import java.util.HashMap;
import java.util.Map;

public class FractiontoRecurringDecimal {
    public static void test(String args[]) {
        System.out.println(fractionToDecimal(1, 2));
        System.out.println(fractionToDecimal(2, 1));
        System.out.println(fractionToDecimal(2, 3));
        System.out.println(fractionToDecimal(0, -5));
        System.out.println(fractionToDecimal(17, 41));
        System.out.println(fractionToDecimal(17, -41));
        System.out.println(fractionToDecimal(-1, -2147483648));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";
        long numer = numerator, denom = denominator;
        StringBuilder sb = new StringBuilder();
        boolean isNeg = false;
        if (numer < 0) {
            isNeg = !isNeg;
            numer = -numer;
        }
        if (denom < 0) {
            isNeg = !isNeg;
            denom = -denom;
        }

        if (isNeg)
            sb.append('-');
        sb.append(numer/denom);
        numer %= denom;
        if (numer > 0)
            sb.append('.');
        Map<Long, Integer> numTbl = new HashMap<>();
        while (numer > 0) {
            Integer startIdx = numTbl.get(numer);
            if (startIdx == null) {
                numTbl.put(numer, sb.length());
                numer *= 10;
                sb.append(numer/denom);
                numer %= denom;
            } else {
                sb.insert(startIdx.intValue(), '(');
                sb.append(')');
                break;
            }
        }
        return sb.toString();
    }


}
