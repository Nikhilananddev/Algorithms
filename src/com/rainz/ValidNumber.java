package com.rainz;

/**
 * Created by Yu on 2/8/2015.
 */
public class ValidNumber {
    public static void test(String args[]) {
        String[] tests = {
                "-.3", // true
                "2.", // true
                "3", // true
                "0099", // true
                "-2.34e-2", // true
                "-2.e-2", // true
                "-.2e-2", // true
                "2.34e2", // true
                "+.e-2", // false
                ".-4", // false
                "+", // false
                "+e3", // false
                "+1e", // false
                "+1e+", // false
                "-2.34e-2.2", // false
                "2.34e-", // false
                "2.3e.2", // false
                "e2", // false
                "e", // false
                "", //false
        };
        for (String t: tests) {
            System.out.println(t + ": " + isNumber(t));
        }
    }

    enum IntState {
        NOT_INT,
        IS_INT,
        EMPTY
    }
    public static IntState checkInt(String s, boolean allowSign) {
        if (s.isEmpty())
            return IntState.EMPTY; // empty string
        int idx = 0;
        if (allowSign && (s.charAt(idx) == '+' || s.charAt(idx) == '-')) {
            ++idx;
            if (s.length() == 1)
                return IntState.EMPTY; // only sign, no digit
        }
        while (idx < s.length()) {
            char c = s.charAt(idx);
            if (c < '0' || c > '9')
                return IntState.NOT_INT; // non-digit char found
            ++idx;
        }
        return IntState.IS_INT;
    }

    public static boolean isNumber(String s) {
        // Split string into mantissa and exponent
        // If E is present, then both sides must have something
        String sTrim = s.trim();
        String[] partsExp = sTrim.split("[Ee]", 2);
        String mantissa = partsExp[0];
        // If 'E' or 'e' exists, we'll have two (possibly empty) strings
        String exp =  partsExp.length > 1 ? partsExp[1] : null;
        // Split mantissa into integer part and decimal part
        String[] partsDot = mantissa.split("\\.", 2);
        IntState stateInt = checkInt(partsDot[0], true);
        IntState stateDec = partsDot.length == 1 ? IntState.EMPTY : checkInt(partsDot[1], false);
        //System.out.println(mantissa + "," + partsDot[0] + "," + partsDot[1] + "," + stateInt.name() + "," + stateDec.name());
        if (stateInt == IntState.EMPTY && stateDec == IntState.EMPTY)
            return false; // has neither integer nor decimal numbers
        if (stateInt == IntState.NOT_INT || stateDec == IntState.NOT_INT)
            return false; // either integer or decimal numbers contains non-digit chars
        // Now check the exponent
        if (exp == null)
            return true;
        return checkInt(exp, true) == IntState.IS_INT;
    }
}
