package com.rainz;

/*
 * Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the string becomes the lexicographically smallest possible string that isn't a palindrome.
 * After doing so, return the final string.  If there is no way to do so, return the empty string.
 */
public class BreakaPalindrome {
    public static void test(String args[]) {
        System.out.println(breakPalindrome("abccba"));
        System.out.println(breakPalindrome("a"));
    }
    public static String breakPalindrome(String palindrome) {
        int L = palindrome.length();
        if (L == 1)
            return "";
        int midIdx = L % 2 == 1 ? L/2 : -1;
        StringBuilder sb = new StringBuilder();
        sb.append(palindrome);
        boolean changed = false;
        for (int i = 0; i < L; ++i) {
            if (i == midIdx)
                continue;
            char c = palindrome.charAt(i);
            if (c > 'a') {
                sb.setCharAt(i, 'a');
                changed = true;
                break;
            }
        }
        if (!changed)
            sb.setCharAt(sb.length()-1, 'b');
        return sb.toString();
    }
}
