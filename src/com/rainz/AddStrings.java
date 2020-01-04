package com.rainz;

public class AddStrings {
    public static void test(String args[]) {
        System.out.println(addStrings("123", "9999"));
        System.out.println(addStrings("0", "0"));
        System.out.println(addStrings("1", "999"));
    }

    public static String addStrings(String num1, String num2) {
        int N1 = num1.length();
        int N2 = num2.length();
        int len = Math.max(N1, N2) + 1;
        char[] outBuffer = new char[len];
        int carry = 0;
        int idx1 = N1 - 1;
        int idx2 = N2 - 1;
        int outIdx;
        for (outIdx = len - 1; outIdx >= 0; --outIdx) {
            int d1 = idx1 >= 0 ? num1.charAt(idx1--) - '0' : 0;
            int d2 = idx2 >= 0 ? num2.charAt(idx2--) - '0' : 0;
            int d = d1 + d2 + carry;
            if (d > 9) {
                d -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            outBuffer[outIdx] = (char)('0' + d);
        }
        outIdx = 0;
        while (outIdx < outBuffer.length-1 && outBuffer[outIdx] == '0')
            ++outIdx;
        return new String(outBuffer, outIdx, outBuffer.length-outIdx);
    }
}
