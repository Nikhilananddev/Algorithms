package com.rainz;

import java.util.*;

/*
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 */
public class ReorderedPowerof2 {
    public static void test(String args[]) {
        System.out.println(reorderedPowerOf2(1));
        System.out.println(reorderedPowerOf2(10));
        System.out.println(reorderedPowerOf2(16));
        System.out.println(reorderedPowerOf2(24));
        System.out.println(reorderedPowerOf2(46));
    }

    public static int sortedDigits(int n)
    {
        List<Character> str = new ArrayList<>();
        int num = n;
        int zeroCount = 0;
        while (num > 0) {
            int digit = num % 10;
            if (digit != 0)
                str.add((char)('0' + digit));
            else
                ++zeroCount;
            num /= 10;
        }
        Collections.sort(str);

        StringBuilder sb = new StringBuilder();
        sb.append(str.get(0));
        for (int i = 0; i < zeroCount; ++i)
            sb.append('0');
        for (int i = 1; i < str.size(); ++i)
            sb.append(str.get(i));
        return Integer.parseInt(sb.toString());
    }

    public static boolean reorderedPowerOf2(int N) {
        Set<Integer> p2Sorted = new HashSet<>();
        int p2 = 1;
        for (int shift = 0; shift < 31; ++shift) {
            p2Sorted.add(sortedDigits(p2 << shift));
        }
        return p2Sorted.contains(sortedDigits(N));
    }
}
