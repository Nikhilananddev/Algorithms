package com.rainz;

/*
 * Given an integer number n, return the difference between the product of its digits and the sum of its digits.
 */

public class SubtracttheProductandSumofDigitsofanInteger {
    public static void test(String args[]) {
        System.out.println(subtractProductAndSum(234));
        System.out.println(subtractProductAndSum(4421));
    }
    public static int subtractProductAndSum(int n) {
        int num = n;
        int sum = 0;
        int prod = 1;
        while (num > 0) {
            int d = num % 10;
            num /= 10;
            sum += d;
            prod *= d;
        }
        return prod - sum;
    }
}
