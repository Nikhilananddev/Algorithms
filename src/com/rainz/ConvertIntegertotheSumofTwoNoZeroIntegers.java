package com.rainz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Given an integer n. No-Zero integer is a positive integer which doesn't contain any 0 in its decimal representation.
 * Return a list of two integers [A, B] where:
 * A and B are No-Zero integers.
 * A + B = n
 * It's guarateed that there is at least one valid solution. If there are many valid solutions you can return any of them.
 */
public class ConvertIntegertotheSumofTwoNoZeroIntegers {
    public static void test(String args[]) {
        Main.printArray(getNoZeroIntegers(2));
        Main.printArray(getNoZeroIntegers(11));
        Main.printArray(getNoZeroIntegers(10000));
        Main.printArray(getNoZeroIntegers(69));
        Main.printArray(getNoZeroIntegers(1010));
        Main.printArray(getNoZeroIntegers(214));
    }
    public static int[] getNoZeroIntegers(int n) {
        List<Integer> digits = new ArrayList<>();
        int num = n;
        while (num > 0) {
            digits.add(num%10);
            num /= 10;
        }
        int borrow = 0;
        List<Integer> other = new ArrayList<>();
        for (int i = 0; i < digits.size(); ++i) {
            int d = digits.get(i);
            int afterBorrow = d - borrow;
            if (afterBorrow >= 2) {
                other.add(1);
                borrow = 0;
                continue;
            }
            if (i == digits.size()-1)
                break;
            if (afterBorrow == 1) {
                other.add(2);
                borrow = 1;
            } else if (afterBorrow <= 0) {
                other.add(1);
                borrow = 1;
            }
        }
        Collections.reverse((other));
        int b = 0;
        for (int d: other)
            b = b*10 + d;
        int[] ans = {n - b, b};
        return ans;
    }
}
