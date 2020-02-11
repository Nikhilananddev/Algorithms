package com.rainz;

/*
 * Given two strings s and t, determine if they are both one edit distance apart.
 * Note:
 * There are 3 possiblities to satisify one edit distance apart:
 * Insert a character into s to get t
 * Delete a character from s to get t
 * Replace a character of s to get t
 */

public class OneEditDistance {
    public static void test(String args[]) {
        System.out.println(isOneEditDistance("ab", "acb"));
        System.out.println(isOneEditDistance("cab", "ad"));
        System.out.println(isOneEditDistance("1203", "1213"));
        System.out.println(isOneEditDistance("", "A"));
        System.out.println(isOneEditDistance("abc", "bcd"));
    }
    // You can also just check length difference. It'll make the logical simpler.
    public static boolean isOneEditDistance(String s, String t) {
        int sIdx = 0, tIdx = 0;
        int diff = 0;
        int S = s.length();
        int T = t.length();

        while (sIdx < S && tIdx < T && diff <= 1) {
            char sc = s.charAt(sIdx);
            char tc = t.charAt(tIdx);
            if (sc == tc) {
                ++sIdx; ++tIdx;
                continue;
            }
            ++diff;
            if (tIdx < T-1 && sc == t.charAt(tIdx+1))
                ++tIdx;
            else if (sIdx < S-1 && s.charAt(sIdx+1) == tc)
                ++sIdx;
            else {
                ++tIdx;
                ++sIdx;
            }
        }
        if (diff > 1)
            return false;
        if (sIdx < S)
            diff += S - sIdx;
        else if (tIdx < T)
            diff += T - tIdx;

        return diff == 1;
    }
}
