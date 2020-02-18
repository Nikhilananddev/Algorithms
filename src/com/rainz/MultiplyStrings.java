package com.rainz;

/**
 * Created by Yu on 2/3/2015.
 */
public class MultiplyStrings {
    public static void test(String args[]) {
        System.out.println(multiply("999", "99"));
        System.out.println(multiply("999", "0"));
        System.out.println(multiply("0", "0"));
    }

    public static String multiply(String num1, String num2) {
        int N1 = num1.length(), N2 = num2.length();
        int N = N1 + N2;
        int[] result = new int[N1 + N2];
        int carry = 0;
        int outIdx = N - 1;
        // idx1 & idx2 counts backwards from the end
        for (int idx1 = 0; idx1 < N1; ++idx1) {
            int d1 = num1.charAt(N1 - 1 - idx1) - '0';
            carry = 0; // reset carry for each row
            for (int idx2 = 0; idx2 < N2; ++idx2) {
                int d2 = num2.charAt(N2 - 1 - idx2) - '0';
                int product = d1 * d2;
                outIdx = N - idx1 - idx2 - 1; // note the output index
                result[outIdx] += product + carry;
                carry = result[outIdx] / 10;
                result[outIdx] %= 10;
            }
            result[outIdx-1] = carry; //carry should be handled at the end of each row
        }
        int startIdx = 0;
        // Skip leading 0's (otherwise 123*0=000) unless answer is 0
        while (startIdx < N-1 && result[startIdx] == 0)
            ++startIdx;
        StringBuilder bldr = new StringBuilder();
        for (int i = startIdx; i < N; ++i)
            bldr.append(result[i]);
        return bldr.toString();
    }

}
