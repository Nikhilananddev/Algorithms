package com.rainz;

/*
 * Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring with repeated characters.
 */

public class SwapForLongestRepeatedCharacterSubstring {
    public static void test(String args[]) {
        System.out.println(maxRepOpt1("ababa"));
        System.out.println(maxRepOpt1("aaabaaa"));
        System.out.println(maxRepOpt1("aaabbaaa"));
        System.out.println(maxRepOpt1("aaaaa"));
        System.out.println(maxRepOpt1("abcdef"));
    }

    public static int maxRepOpt1(String text) {
        if (text.length() == 0)
            return 0;
        int ans = 0;
        int[] allFreqs = new int[26];
        for (int i = 0; i < text.length(); ++i)
            ++allFreqs[text.charAt(i)-'a'];

        int diffs = 0;
        int diffIdx = -1;
        char runChar = text.charAt(0);
        int runLen = 1;
        int i = 1;
        while (i <= text.length()) {
            char c = i < text.length() ? text.charAt(i) : '\0';
            if (c == runChar) {
                ++runLen;
            } else {
                if (diffs == 0) {
                    diffs = 1;
                    diffIdx = i;
                    // handle case where last part of string comprises of same chars.
                    if (runLen > ans)
                        ans = runLen;
                } else {
                    // See 2nd diff
                    if (runLen < allFreqs[runChar-'a'])
                        ++runLen; // has additional "lastChar"s to swap from
                    if (runLen > ans)
                        ans = runLen;
                    // Move back to 1st diff and restart there
                    i = diffIdx;
                    runChar = text.charAt(i);
                    runLen = 1;
                    diffs = 0;
                }
            }
            ++i;
        }
        return ans;
    }
}
