package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 */

public class SequentialDigits {
    public static void test(String args[]) {
        System.out.println((sequentialDigits(58, 155)).toString());
        System.out.println((sequentialDigits(100, 300)).toString());
        System.out.println((sequentialDigits(1000, 13000)).toString());
        System.out.println((sequentialDigits(10, 1000000000)).toString());
    }

    public static List<Integer> sequentialDigits(int low, int high) {
        int numDigits = 0;
        int firstDigit = 0;
        int tmp = low;
        while (tmp > 0) {
            firstDigit = tmp % 10;
            tmp /= 10;
            ++numDigits;
        }
        List<Integer> result = new ArrayList<>();
        int num = 0;
        // Don't forget to check numDigits < 10
        for (;numDigits < 10;) {
            num = 0;
            int lastDigit = firstDigit + numDigits - 1;
            if (lastDigit > 9) {
                // Time to increase length
                firstDigit = 1;
                ++numDigits;
                continue;
            }
            for (int digit = firstDigit; digit <= lastDigit; ++digit) {
                num = num*10 + digit;
            }
            if (num <= high) {
                if (num >= low)
                    result.add(num);
            }
            else
                break;
            ++firstDigit;
        }

        return result;
    }
}
