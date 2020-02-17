package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string representing an expression of fraction addition and subtraction, you need to return the calculation result in string format. The final result should be irreducible fraction. If your final result is an integer, say 2, you need to change it to the format of fraction that has denominator 1. So in this case, 2 should be converted to 2/1.
 */
public class FractionAdditionandSubtraction {
    public static void test(String args[]) {
        System.out.println(fractionAddition("-1/2+1/2"));
        System.out.println(fractionAddition("-1/2+1/2+1/3"));
        System.out.println(fractionAddition("1/3-1/2"));
        System.out.println(fractionAddition("5/3+1/3"));
        System.out.println(fractionAddition("-4/7-3/4+2/3"));
    }

    private static int computeGCD(int a, int b) {
        if (b > a) {
            int tmp = b;
            b = a;
            a = tmp;
        }
        while (b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static String fractionAddition(String expression) {
        if (expression.length() == 0)
            return "0/1";
        if (Character.isDigit(expression.charAt(0)))
            expression = "+" + expression;
        int currN = 0, currD = 1;
        int currSign = 1;
        String[] parts = expression.split("[+-]");
        List<Integer> signs = new ArrayList<>();
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (c == '+')
                signs.add(1);
            else if (c == '-')
                signs.add(-1);
        }
        for (int i = 1; i < parts.length; ++i) {
            String p = parts[i];
            int sign = signs.get(i-1);
            String[] nd = p.split("/");
            int nm = Integer.parseInt(nd[0]);
            int dn = Integer.parseInt(nd[1]);
            int gcd = computeGCD(currD, dn);
            int lcm = currD/gcd*dn;
            int m1 = lcm/currD, m2 = lcm/dn;
            sign = currSign == sign ? 1 : -1;
            currN = currSign*(currN*m1 + sign*nm*m2);
            currD = lcm;
            if (currN < 0) {
                currN = -currN;
                currSign = -1;
            } else if (currN == 0) {
                currD = 1;
                currSign = 1;
            } else
                currSign = 1;
            // Reduce the result
            int gcdND = computeGCD(currN, currD);
            currN /= gcdND;
            currD /= gcdND;
        }
        StringBuilder sb = new StringBuilder();
        if (currSign < 0)
            sb.append('-');
        sb.append(currN);
        sb.append('/');
        sb.append(currD);
        return sb.toString();
    }



}
