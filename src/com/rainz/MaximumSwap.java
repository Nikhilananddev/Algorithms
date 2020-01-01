package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
 */

public class MaximumSwap {
    public static void test(String args[]) {
        System.out.println(maximumSwap(2736));
        System.out.println(maximumSwap(9973));
    }

    public static int maximumSwap(int num) {
        if (num == 0)
            return num;
        List<Integer> digits = new ArrayList<>();
        while (num > 0) {
            digits.add(num % 10);
            num /= 10;
        }
        List<Integer> maxIndices = new ArrayList<>();
        maxIndices.add(0); // max for first digit is just itself
        for (int i = 1; i < digits.size(); ++i) {
            int d = digits.get(i);
            int prevMaxIdx = maxIndices.get(i-1);
            if (d > digits.get(prevMaxIdx))
                maxIndices.add(i);
            else
                maxIndices.add(prevMaxIdx);
        }
        for (int i = digits.size()-1; i >= 0; --i) {
            int d = digits.get(i);
            int maxIdx = maxIndices.get(i);
            int maxVal = digits.get(maxIdx);
            if (maxVal > d) {
                // Swap
                digits.set(i, maxVal);
                digits.set(maxIdx, d);
                break;
            }
        }
        num = 0;
        for (int i = digits.size()-1; i >= 0; --i)
            num = num * 10 + digits.get(i);

        return num;
    }
}
