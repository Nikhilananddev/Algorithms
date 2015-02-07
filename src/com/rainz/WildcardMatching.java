package com.rainz;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Yu on 2/3/2015.
 */
public class WildcardMatching {
    public static void test(String args[]) {
        System.out.println(isMatch("abcd", "a*d")); // true
        System.out.println(isMatch("abcd", "*a?cd*")); // true
        System.out.println(isMatch("abcdefgh", "a*cd*e*g*h")); // true
        System.out.println(isMatch("abcd", "***")); // true
        System.out.println(isMatch("baa", "*a")); // true
        System.out.println(isMatch("", "*")); // true
        System.out.println(isMatch("", "")); // true
        System.out.println(isMatch("abcde", "ab*d")); // false
        System.out.println(isMatch("abcd", "abc*e")); // false
        System.out.println(isMatch("abcd", "abcde")); // false
        System.out.println(isMatch("aa", "a")); // false
    }

    public static boolean isMatch(String s, String p) {
        return isMatchBT(s, p);
        //return isMatchDP2(s, p);
        //return isMatchStack(s, p);
        //return isMatchRecursive(s, p);
        //return isMatchDP(s, p);
    }

    public static boolean isMatchBT(String s, String p) {
        int sIdx = 0, pIdx = 0;
        int sSav = -1, pStar = -1;
        while (sIdx < s.length()) {
            if (pIdx < p.length()) {
                char sChar = s.charAt(sIdx);
                char pChar = p.charAt(pIdx);
                if (sChar == pChar || pChar == '?') {
                    // Char match
                    ++sIdx;
                    ++pIdx;
                    continue;
                }
                if (pChar == '*') {
                    // No match, pattern is *
                    sSav = sIdx;
                    pStar = pIdx++;
                    continue;
                }
            }
            if (pStar >= 0) {
                // No match, can still backtrack
                sIdx = ++sSav;
                pIdx = pStar + 1;
            } else {
                // No match
                return false;
            }
        }
        while (pIdx < p.length()) {
            // s reaches end, but p has more
            if (p.charAt(pIdx) != '*')
                return false;
            ++pIdx;
        }
        return true;
    }

    public static boolean isMatchDP2(String s, String p) {
        boolean[] dpCol = new boolean[s.length()+1]; // +1 is for empty string
        boolean[] dpColOld = new boolean[s.length()+1];
        dpColOld[s.length()] = true;
        for (int i = s.length()-1; i >=0; --i)
            dpColOld[i] = false;
        for (int pIdx = p.length()-1; pIdx >= 0; --pIdx) {
            char pChar = p.charAt(pIdx);
            dpCol[s.length()] = pChar == '*' ? dpColOld[s.length()] : false;
            for (int sIdx = s.length()-1; sIdx >= 0; --sIdx) {
                char sChar = s.charAt(sIdx);
                if (sChar == pChar || pChar == '?')
                    dpCol[sIdx] = dpColOld[sIdx+1];
                else if (pChar != '*')
                    dpCol[sIdx] = false;
                else {
                    dpCol[sIdx] = false;
                    for (int j = sIdx; j <= s.length(); ++j) {
                        if (dpColOld[j]) {
                            dpCol[sIdx] = true;
                            break;
                        }
                    }
                }
            }
            boolean[] tmp = dpCol;
            dpCol = dpColOld;
            dpColOld = tmp;
        }
        return dpColOld[0];
    }

    public static boolean isMatchStack(String s, String p) {
        int sIdx = 0, pIdx = 0;
        Stack<Integer> sStack = new Stack<Integer>();
        Stack<Integer> pStack = new Stack<Integer>();

        while (sIdx <= s.length() || pIdx <= p.length()) {
            if (sIdx == s.length()) {
                // s reaches end, check if p also reaches end or has only '*'s left
                boolean allStars = true;
                while (pIdx < p.length()) {
                    if (p.charAt(pIdx) != '*') {
                        allStars = false;
                        break;
                    }
                    ++pIdx;
                }
                if (allStars)
                    return true;
                // Doesn't match, pop the stack or return false if stack is empty
                if (sStack.empty())
                    return false;
                sIdx = sStack.pop();
                pIdx = pStack.pop();
                continue;
            }
            if (pIdx == p.length()) {
                // p reaches end, s hasn't
                if (sStack.empty())
                    return false;
                sIdx = sStack.pop();
                pIdx = pStack.pop();
                continue;
            }
            if (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '?') {
                // If the two chars match
                ++sIdx;
                ++pIdx;
            } else if (p.charAt(pIdx) != '*') {
                // The two chars don't match and p is not pointing at '*', so mismatch
                if (sStack.empty())
                    return false;
                sIdx = sStack.pop();
                pIdx = pStack.pop();
            } else {
                // Now pIdx points to a '*'
                sStack.push(sIdx + 1);
                pStack.push(pIdx);
                ++pIdx;
            }
        }
        return true; // shouldn't get here
    }

    public static boolean isMatchDP(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1]; // +1 is for empty string
        dp[s.length()][p.length()] = true;
        // Bottom row, ie, s is empty
        for (int i = p.length()-1; i >= 0; --i)
            dp[s.length()][i] = p.charAt(i) != '*' ? false : dp[s.length()][i+1];
        // Right column, ie, p is empty
        for (int i = s.length()-1; i >=0; --i)
            dp[i][p.length()] = false;
        for (int sIdx = s.length()-1; sIdx >= 0; --sIdx) {
            for (int pIdx = p.length()-1; pIdx >= 0; --pIdx) {
                char sChar = s.charAt(sIdx);
                char pChar = p.charAt(pIdx);
                if (sChar == pChar || pChar == '?') {
                    dp[sIdx][pIdx] = dp[sIdx + 1][pIdx + 1];
                    continue;
                }
                if (pChar != '*') {
                    dp[sIdx][pIdx] = false;
                    continue;
                }
                // Now pChar is '*'
                boolean starMatch = false;
                for (int dpIdx = sIdx; dpIdx <= s.length(); ++dpIdx) { // note the "<="
                    if (dp[dpIdx][pIdx + 1]) {
                        starMatch = true;
                        break;
                    }
                }
                dp[sIdx][pIdx] = starMatch;
            }
        }
        /*
        for (boolean[] row: dp) {
            for (boolean b: row)
                System.out.print(""+(b?"T":"F")+" ");
            System.out.println();
        }
        */
        return dp[0][0];
    }

    protected static HashMap<String, Boolean> memoization;
    protected static boolean helper(String s, int sIdx, String p, int pIdx) {
        String key = "" + sIdx + "_" + pIdx;
        Boolean cacheVal = memoization.get(key);
        if (cacheVal != null)
            return cacheVal.booleanValue();
        while (sIdx < s.length() && pIdx < p.length()) {
            if (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '?') {
                ++sIdx;
                ++pIdx;
                continue;
            }
            if (p.charAt(pIdx) != '*') {
                memoization.put(key, false);
                return false;
            }
            for (int idx = sIdx; idx <= s.length(); ++idx) { // note the <= here !!
                if (helper(s, idx, p, pIdx + 1)) {
                    memoization.put(key, true);
                    return true;
                }
            }
            memoization.put(key, false);
            return false;
        }

        // Base cases
        if (sIdx == s.length()) {
            // Now s reaches end, p either reaches end or has only '*'s left
            while (pIdx < p.length()) {
                if (p.charAt(pIdx) != '*') {
                    memoization.put(key, false);
                    return false;
                }
                ++pIdx;
            }
            memoization.put(key, true);
            return true;
        }
        // p reaches end, s hasn't
        memoization.put(key, false);
        return false;
    }

    public static boolean isMatchRecursive(String s, String p) {
        memoization = new HashMap<String, Boolean>();
        return helper(s, 0, p, 0);
    }

}
