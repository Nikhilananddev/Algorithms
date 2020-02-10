package com.rainz;

public class PalindromicSubstrings {
    public static void test(String args[]) {
        System.out.println(countSubstrings("abc"));
        System.out.println(countSubstrings("aaa"));
        System.out.println(countSubstrings("aaaa"));
        System.out.println(countSubstrings("abcbca"));
    }

    public static int countSubstrings(String s) {
        //return countSubstringsOld(s);
        return countSubstringsDp(s);
    }
    public static int countSubstringsDp(String s) {
        int L = s.length();
        int ans = 0;
        boolean[][] isPal = new boolean[L][L];
        for (int len = 1; len <= L; ++len) {
            for (int i = 0; i + len <= L; ++i) {
                int j = i + len - 1;
                isPal[i][j] = (s.charAt(i) == s.charAt(j) && (j-i <= 2 || isPal[i+1][j-1]));
                if (isPal[i][j])
                    ++ans;
            }
        }
        return ans;
    }
    public static int countSubstringsOld(String s) {
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
