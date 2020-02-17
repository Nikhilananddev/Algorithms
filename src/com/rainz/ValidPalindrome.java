package com.rainz;

/*
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 */

public class ValidPalindrome {
    public static void test(String args[]) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }

    public static boolean isPalindrome(String s) {
        int start = 0, end = s.length()-1;
        while (start < end) {
            char sc = Character.toLowerCase(s.charAt(start));
            if (!Character.isLetter(sc) && !Character.isDigit(sc)) {
                ++start;
                continue;
            }
            char ec = Character.toLowerCase(s.charAt(end));
            if (!Character.isLetter(ec) && !Character.isDigit(ec)) {
                --end;
                continue;
            }
            if (sc != ec)
                return false;
            ++start;
            --end;
        }
        return true;
    }
}
