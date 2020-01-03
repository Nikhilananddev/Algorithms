package com.rainz;

import java.util.Arrays;

/*
 * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
 * S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.
 * Return any permutation of T (as a string) that satisfies this property.
 */
public class CustomSortString {
    public static void test(String args[]) {
        System.out.println(customSortString("cba", "abcd"));
    }

    public static String customSortString(String S, String T) {
        int[] sortOrder = new int[26];
        Arrays.fill(sortOrder, 26);
        for (int i = 0; i < S.length(); ++i)
            sortOrder[S.charAt(i)-'a'] = i;
        Character[] outStr = new Character[T.length()];
        for (int i = 0; i < T.length(); ++i) {
            outStr[i] = T.charAt(i);
        }
        Arrays.sort(outStr, (x, y) -> sortOrder[(int)x-'a'] - sortOrder[(int)y-'a']);
        char[] ans = new char[outStr.length];
        for (int i = 0; i < outStr.length; ++i) {
            ans[i] = outStr[i];
        }
        return new String(ans);
    }
}
