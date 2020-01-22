package com.rainz;

import java.util.ArrayList;
import java.util.List;
/*
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Find all strobogrammatic numbers that are of length = n.
 */
public class StrobogrammaticNumberII {
    public static void test(String args[]) {
        System.out.println(findStrobogrammatic(1));
        System.out.println(findStrobogrammatic(2));
    }

    private static void helper(int n, StringBuilder sb, int start, List<String> ans) {
        if (start >= (n+1)/2) {
            ans.add(sb.toString());
            return;
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
            helper(n, sb, start+1, ans);
        }
    }

    public static List<String> findStrobogrammatic(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.setLength(n);
        helper(n, sb, 0, ans);
        return ans;
    }

}
