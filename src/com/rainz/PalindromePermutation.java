package com.rainz;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a string, determine if a permutation of the string could form a palindrome.
 */
public class PalindromePermutation {
    public static void test(String args[]) {
        System.out.println(canPermutePalindrome("code"));
        System.out.println(canPermutePalindrome("aab"));
        System.out.println(canPermutePalindrome("carerac"));
    }

    public static boolean canPermutePalindrome(String s) {
        Map<Character, Integer> freqs = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            freqs.put(c, freqs.getOrDefault(c, 0)+1);
        }
        int oddCount = 0;
        for (int cnt: freqs.values()) {
            if (cnt % 2 == 1) {
                ++oddCount;
                if (oddCount > 1)
                    return false;
            }
        }
        return true;
    }
}
