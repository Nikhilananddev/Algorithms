package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * A Stepping Number is an integer such that all of its adjacent digits have an absolute difference of exactly 1. For example, 321 is a Stepping Number while 421 is not.
 * Given two integers low and high, find and return a sorted list of all the Stepping Numbers in the range [low, high] inclusive.
 */
public class SteppingNumbers {
    public static void test(String args[]) {
        System.out.println(countSteppingNumbers(0, 21));
    }

    private static void helper(String minStr, String maxStr, int len, StringBuilder sb, List<Integer> ans) {
        int idx = sb.length();
        if (idx == len) {
            String num = sb.toString();
            if (num.compareTo(minStr) >= 0 && num.compareTo(maxStr) <= 0)
                ans.add(Integer.parseInt(num));
            return;
        }
        char c = sb.charAt(idx-1);
        char c1 = (char)(c - 1);
        if (c1 >= '0') {
            sb.append(c1);
            helper(minStr, maxStr, len, sb, ans);
            sb.deleteCharAt(idx);
        }
        c1 = (char)(c + 1);
        if (c1 <= '9') {
            sb.append(c1);
            helper(minStr, maxStr, len, sb, ans);
            sb.deleteCharAt(idx);
        }
    }

    public static List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        if (low == 0) {
            ans.add(0);
            low = 1;
        }
        String lowStr = Integer.toString(low);
        String hiStr = Integer.toString(high);
        for (int len = lowStr.length(); len <= hiStr.length(); ++len) {
            String minStr, maxStr;
            if (len == lowStr.length()) {
                minStr = lowStr;
            } else {
                char[] buf = new char[len];
                Arrays.fill(buf, '0');
                buf[0] = '1';
                minStr = new String(buf);
            }
            if (len == hiStr.length()) {
                maxStr = hiStr;
            } else {
                char[] buf = new char[len];
                Arrays.fill(buf, '9');
                maxStr = new String(buf);
            }
            for (char c = minStr.charAt(0); c <= maxStr.charAt(0); ++c) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                helper(minStr, maxStr, len, sb, ans);
            }
        }
        return ans;
    }
}
