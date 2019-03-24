package com.rainz;

public class ShiftingLetters {
    public static void test(String args[]) {
        int[] input = {3,5,9};
        System.out.println(shiftingLetters("abc", input));

        int[] input2 = {505870226,437526072,266740649,224336793,532917782,311122363,567754492,595798950,81520022,684110326,137742843,275267355,856903962,148291585,919054234,467541837,622939912,116899933,983296461,536563513};
        System.out.println(shiftingLetters("mkgfzkkuxownxvfvxasy",
                input2));
    }

    public static String shiftingLetters(String S, int[] shifts) {
        int[] sums = new int[shifts.length];
        int lastIdx = shifts.length - 1;
        sums[lastIdx] = shifts[lastIdx];
        for (int i = lastIdx-1; i >= 0; --i)
            sums[i] = (sums[i+1] + shifts[i]) % 26;
        char[] output = new char[S.length()];
        for (int i = 0; i < output.length; ++i) {
            output[i] = (char)((sums[i] + S.charAt(i) - 'a') % 26 + 'a');
        }
        return new String(output);
    }
}
