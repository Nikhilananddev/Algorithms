package com.rainz;

/*
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 * Return true if and only if you can transform str1 into str2.
 */

import java.util.HashSet;
import java.util.Set;

public class StringTransformsIntoAnotherString {
    public static void test(String args[]) {
        String str11 = "aabcc", str12 = "ccdee";
        System.out.println(canConvert(str11, str12));
        String str21 = "leetcode", str22 = "codeleet";
        System.out.println(canConvert(str21, str22));
    }

    /*
     * Key observation: if a char from str1 maps to different chars in str2, then conversion is impossible.
     * Based on this we can conclude that, if str2 contains all 26 letters, unless str1==str2, conversion is impossible.
     * This is because str1 has no "temp" char which doesn't exist in str2 to do conversion.
     * Example: str1="abcd....xyz", str2="bcde....yza".
     * - If you convert a to b in str1, then the 2 b's map to different chars in str2, violating our key observation.
     * - So you need a temp char for a to convert to. But since str2 used up all 26 letters. Such temp char doesn't exist.
     *
     * If str1 & str2 doesn't violate our key observation, and str2 contains at most 25 distinct letters, we can always convert.
     * Example: str1="abcd....wxyz", str2="bcde....xyaa". str2 doesn't contain z.
     * First, remember z->a, and convert a->z, then you can freely convert y->a, x->y, w->x, etc. At the end you convert z->a.
     */
    public static boolean canConvert(String str1, String str2) {
        Set<Character> strChars = new HashSet<>();
        char[] mappings = new char[26];
        for (int i = 0; i < str1.length(); ++i) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            strChars.add(c2);
            if (strChars.size() == 26)
                return str1.compareTo(str2) == 0;
            char map = mappings[c1-'a'];
            if (map == '\0')
                mappings[c1-'a'] = c2;
            else if (map != c2)
                return false;
        }
        return true;
    }

}
