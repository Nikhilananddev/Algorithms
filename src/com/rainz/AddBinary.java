package com.rainz;

/**
 * Created by Yu on 2/21/2015.
 */
public class AddBinary {
    public static void test(String args[]) {
        System.out.println(addBinary("1010", "1011"));
    }

    public static String addBinary(String a, String b) {
        int outLen = Math.max(a.length(), b.length());
        char[] out = new char[outLen];
        int carry = 0;
        int processed = 0;
        do {
            int idxA = a.length() - 1 - processed;
            int idxB = b.length() - 1 - processed;
            char aChar = idxA >= 0 ? a.charAt(idxA) : '0';
            char bChar = idxB >= 0 ? b.charAt(idxB) : '0';
            int sum = aChar - '0' + bChar - '0' + carry;
            if (sum > 1) {
                carry = 1;
                sum &= 1;
            } else
                carry = 0;
            out[outLen - 1 - processed] = (char)('0' + sum);
            ++processed;
        } while (processed < outLen);
        StringBuilder bldr = new StringBuilder(outLen+1);
        if (carry == 1)
            bldr.append('1');
        bldr.append(out);
        return bldr.toString();
    }
}
