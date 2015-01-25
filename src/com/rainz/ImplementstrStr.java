package com.rainz;

/**
 * Created by Yu on 1/25/2015.
 */
public class ImplementstrStr {
    public static void test(String args[]) {
        System.out.println(strStr("", ""));
        System.out.println(strStr("abc", ""));
        System.out.println(strStr("abcdefg", "bcd"));
        System.out.println(strStr("abcdefg", "abcd"));
        System.out.println(strStr("abcdefg", "defg"));
        System.out.println(strStr("abcdefg", "bcefg"));
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        for (int hIdx = 0; hIdx < haystack.length(); ++hIdx) {
            boolean match = true;
            for (int nIdx = 0; nIdx < needle.length(); ++nIdx) {
                char nChar = needle.charAt(nIdx);
                int hayIdx = hIdx + nIdx;
                if (hayIdx >= haystack.length())
                    return -1;
                char hChar = haystack.charAt(hayIdx);
                if (nChar != hChar) {
                    match = false;
                    break;
                }
            }
            if (match)
                return hIdx;
        }

        return -1;
    }
}
