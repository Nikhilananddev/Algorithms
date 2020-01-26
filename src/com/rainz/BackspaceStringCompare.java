package com.rainz;

/*
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 */

public class BackspaceStringCompare {
    public static void test(String args[]) {
        System.out.println(backspaceCompare("ab#c", "ad#c"));
        System.out.println(backspaceCompare("ab##", "c#d#"));
        System.out.println(backspaceCompare("a##c", "#a#c"));
        System.out.println(backspaceCompare("a#c", "b"));
        System.out.println(backspaceCompare("bxj##tw", "bxo#j##tw"));
    }
    /*
     * In order to achieve O(n) runtime and const space, we scan backwards from end of strings
     */
    public static boolean backspaceCompare(String S, String T) {
        int sIdx = S.length()-1, tIdx = T.length()-1;
        int sBack = 0, tBack = 0; // when going back from the end, every backspace counts until we reach start of string
        while (sIdx >= 0 || tIdx >= 0) {
            while (sIdx >= 0 && S.charAt(sIdx) == '#') {
                ++sBack;
                --sIdx;
            }
            // Delete up to "back" non-# chars
            while (sIdx >= 0 && sBack > 0 && S.charAt(sIdx) != '#') {
                --sIdx;
                --sBack;
            }
            char s = sIdx >= 0 ? S.charAt(sIdx) : '\0';
            while (tIdx >= 0 && T.charAt(tIdx) == '#') {
                ++tBack;
                --tIdx;
            }
            // Delete up to "back" non-# chars
            while (tIdx >= 0 && tBack > 0 && T.charAt(tIdx) != '#') {
                --tIdx;
                --tBack;
            }
            char t = tIdx >= 0 ? T.charAt(tIdx) : '\0';
            if (s == '#' || t == '#')
                continue; // more #s after delete. Repeat the above.
            // Now s & t are actual chars
            if (s != t)
                return false;
            --sIdx; --tIdx;
        }
        if (sIdx < 0 && tIdx < 0)
            return true;
        return false;
    }
}
