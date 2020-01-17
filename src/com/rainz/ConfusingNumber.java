package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class ConfusingNumber {
    public static void test(String args[]) {
        System.out.println(confusingNumber(6));
        System.out.println(confusingNumber(89));
        System.out.println(confusingNumber(11));
        System.out.println(confusingNumber(25));
        System.out.println(confusingNumber(69));
    }
    public static boolean confusingNumber(int N) {
        if (N == 0)
            return false;
        final int[] rotate = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        List<Integer> digits = new ArrayList<>();
        int num = N;
        while (num > 0) {
            int d = num % 10;
            if (rotate[d] < 0)
                return false;
            digits.add(rotate[d]);
            num /= 10;
        }
        num = 0;
        for (int d: digits)
            num = num*10 + d;
        return num != N;
    }
}
