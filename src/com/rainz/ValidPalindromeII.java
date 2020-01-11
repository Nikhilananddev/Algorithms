package com.rainz;

/*
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 */

public class ValidPalindromeII {
    public static void test(String args[]) {
        System.out.println(validPalindrome("aba"));
        System.out.println(validPalindrome("abca"));
        System.out.println(validPalindrome("abac"));
        System.out.println(validPalindrome("caba"));
        System.out.println(validPalindrome("cabad"));
    }

    private static boolean helper(String s, int start, int end, int diffsLeft) {
        while (start < end) {
            if (s.charAt(start) == s.charAt(end-1)) {
                ++start;
                --end;
            } else {
                if (diffsLeft == 0)
                    return false;
                boolean res = helper(s, start, end-1, 0);
                if (res)
                    return true;
                return helper(s, start+1, end, 0);
            }
        }
        return true;
    }

    public static boolean validPalindrome(String s) {
        return helper(s, 0, s.length(), 1);
    }
}
