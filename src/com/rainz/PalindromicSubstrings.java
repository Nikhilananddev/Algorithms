package com.rainz;

public class PalindromicSubstrings {
    public static void test(String args[]) {
        System.out.println(countSubstrings("abc"));
        System.out.println(countSubstrings("aaa"));
        System.out.println(countSubstrings("aaaa"));
        System.out.println(countSubstrings("abcbca"));
    }

    public static int countSubstrings(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            // Odd palindromes
            int count = 1;
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < s.length() &&
                    s.charAt(left) == s.charAt(right)) {
                ++count;
                --left;
                ++right;
            }
            // Even palindromes
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() &&
                    s.charAt(left) == s.charAt(right)) {
                ++count;
                --left;
                ++right;
            }
            ans += count;
        }

        return ans;
    }
}
