package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 */
public class StrobogrammaticNumberIII {
    public static void test(String args[]) {
        System.out.println(strobogrammaticInRange("50", "100"));
        System.out.println(strobogrammaticInRange("100", "1000"));
    }

    private static int helper(int n, StringBuilder sb, int start, String min, String max, int ans) {
        if (start >= (n+1)/2) {
            String str = sb.toString();
            if ((min == null || min.compareTo(str) <= 0) && (max == null || max.compareTo(str) >= 0))
                ++ans;
            return ans;
        }
        final char[] stroboMap = {'0', '1', 'x', 'x', 'x', 'x', '9', 'x', '8', '6'};
        char digitStart = start > 0 || n == 1? '0' : '1';
        for (char c = digitStart; c <= '9'; ++c) {
            char strobo = stroboMap[c-'0'];
            if (strobo == 'x')
                continue;
            if (n % 2 == 1 && start == n/2 && c != strobo)
                continue; // for odd length, middle char cannot be 6 or 9
            sb.setCharAt(start, c);
            sb.setCharAt(n - 1 - start, strobo);
            ans = helper(n, sb, start+1, min, max, ans);
        }
        return ans;
    }
    public static int strobogrammaticInRange(String low, String high) {
        int ans = 0;
        for (int len = low.length(); len <= high.length(); ++len) {
            StringBuilder sb = new StringBuilder();
            sb.setLength(len);
            String min = len == low.length() ? low : null;
            String max = len == high.length() ? high : null;
            ans = helper(len, sb, 0, min, max, ans);
        }
        return ans;
    }

}
