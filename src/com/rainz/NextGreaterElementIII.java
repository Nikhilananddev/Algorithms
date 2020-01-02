package com.rainz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NextGreaterElementIII {
    public static void test(String args[]) {
        System.out.println(nextGreaterElement(12));
        System.out.println(nextGreaterElement(21));
    }

    public static int nextGreaterElement(int n) {
        List<Integer> digits = new ArrayList<>();
        int num = n;
        while (num > 0) {
            digits.add(num % 10);
            num /= 10;
        }
        for (int i = 1; i < digits.size(); ++i) {
            int d = digits.get(i);
            int idxSwap = -1;
            for (int j = i-1; j >= 0; --j) {
                int prevD = digits.get(j);
                if (prevD > d && (idxSwap < 0 || digits.get(idxSwap) > prevD))
                    idxSwap = j;
            }
            if (idxSwap >= 0) {
                digits.set(i, digits.get(idxSwap));
                digits.set(idxSwap, d);
                // After swapping, make the digits after swap point smallest
                Collections.sort(digits.subList(0, i), Collections.reverseOrder());
                break;
            }
        }
        long newNum = 0;
        long base = 1;
        for (int d: digits) {
            newNum += d * base;
            base *= 10;
        }
        if (newNum == n || newNum > Integer.MAX_VALUE)
            return -1;
        return (int)newNum;
    }
}
