package com.rainz;

/**
 * Created by Yu on 2/21/2015.
 */
public class PlusOne {
    public static void test(String args[]) {
        int[] test = {9,9,9};
        int[] ans = plusOne(test);
        for (int x: ans)
            System.out.print(x);
        System.out.println();
    }

    public static int[] plusOne(int[] digits) {
        int[] num = new int[digits.length];
        int carry = 1, idx = digits.length - 1;
        do {
            int d = digits[idx] + carry;
            if (d > 9) {
                d = 0;
                carry = 1;
            } else {
                carry = 0;
            }
            num[idx] = d;
            --idx;
        } while (idx >= 0);
        if (carry == 0)
            return num;
        int[] numNew = new int[digits.length + 1];
        numNew[0] = 1;
        // No need to copy the rest, since they'll be 0's
        return numNew;
    }
}
