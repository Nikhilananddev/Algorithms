package com.rainz;

/**
 * Created by Yu on 1/19/2015.
 */
public class RegularExpressionMatching {
    public static void test(String args[]) {
        System.out.println(isMatch("abc", "abc")); // true
        System.out.println(isMatch("abc", "a*bc")); // true
        System.out.println(isMatch("abc", ".*bc.*.*")); // true
        System.out.println(isMatch("abc", ".*b.*.*c.*.*")); // true
        System.out.println(isMatch("abc", "a.c")); // true
        System.out.println(isMatch("abcd", "a.c")); // false
        System.out.println(isMatch("abcd", "a.*d")); // true
        System.out.println(isMatch("abcd", "a.c")); // false
        System.out.println(isMatch("abbbbbcd", "ab*cd")); // true
        System.out.println(isMatch("aasfdsdcd", "a.*cd")); // true
        System.out.println(isMatch("aabcd", "a*abcd")); // true
        System.out.println(isMatch("aabcd", "a*abcd.*d")); // false
        System.out.println(isMatch("aabcd", "a*abcd.*d*")); // true
        System.out.println(isMatch(";lfjwapejioj;sdjf;klsd*.", ".*")); // true
        System.out.println(isMatch("", ".*")); // true
        System.out.println(isMatch("", "")); // true
    }

    private static boolean helperOld(String s, int sIdx, String p, int pIdx) {
        while (sIdx < s.length() && pIdx < p.length()) {
            char sChar = s.charAt(sIdx), pChar = p.charAt(pIdx);
            boolean match = (sChar == pChar || pChar == '.');
            if (pIdx == p.length() - 1 || p.charAt(pIdx + 1) != '*') { // if next char in p is not '*'
                if (!match)
                    return false;
                ++sIdx;
                ++pIdx;
                continue;
            }
            // First pretend X* is empty, compare the pattern after X*
            if (helperOld(s, sIdx, p, pIdx + 2))
                return true;
            // Now if the current sChar is not X, and since the remainder of s
            // doesn't match the portion of p after X*, match fails.
            if (!match)
                return false;
            // So X* in p matches the first X in s, move on to the next char in s and try again
            ++sIdx;
        }
        // If s still has more characters, match failed.
        if (sIdx < s.length())
            return false;
        // If pattern still has more, but are in the form of X*, that's okay, skip them
        while (pIdx < p.length() - 1 && p.charAt(pIdx+1) =='*')
            pIdx += 2;
        if (pIdx < p.length())
            return false;
        return true;
    }
    /*
     * Outline:
     * Case 1: p (pattern) is empty, s must be empty
     * Case 2: 2nd char in p is not * (or p has only 1 char), just compare and move on
     * Case 3: 2nd char in p is *, need to try matching x* with multiple x's in s. Here x is 1st char in p
     *   Case 3.1: there is no x in S
     *   Case 3.2: more than one x's in S. Use a for loop.
     */

    private static boolean helper(String s, int sIdx, String p, int pIdx) {
        // Case 1: p (pattern) reaches end, then s must also reaches end
        // Note the other way is not necessarily true: if s reaches end, p could still have "a*", for example
        if (pIdx >= p.length())
            return sIdx == s.length();

        char pChar = p.charAt(pIdx); // first char in p at current position

        // Case 2: if 2nd char in p is not '*' (or p has only 1 char left), just compare the two chars and continue
        if (pIdx == p.length() - 1 || p.charAt(pIdx+1) != '*')
            return sIdx < s.length() && (s.charAt(sIdx) == pChar || pChar == '.') && helper(s, sIdx+1, p, pIdx+1);

        // Case 3: now 2nd char in p is '*', need to try skipping multiple pChars in s,
        // Case 3.1: first try if there is no pChar in s at this position. Examples: ABCDEF vs ABCx*DEF
        if (helper(s, sIdx, p, pIdx+2))
            return true;

        // Case 3.2: try multiple pChars in s. Example: ABCxDEF vs ABCx*DEF, ABCxxDEF vs ABCx*DEF,
        int idx = sIdx;
        while (idx < s.length() && (s.charAt(idx) == pChar || pChar == '.')) {
            if (helper(s, idx+1, p, pIdx+2))
                return true;
            ++idx;
        }
        return false;
    }

    public static boolean isMatch(String s,String p) {
        return helper(s, 0, p, 0);
    }
}
