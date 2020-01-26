package com.rainz;

/*
 * Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.
 * Each letter in the magazine string can only be used once in your ransom note.
 */
public class RansomNote {
    public static void test(String args[]) {
        System.out.println(canConstruct("a", "b")); // -> false
        System.out.println(canConstruct("aa", "ab")); // -> false
        System.out.println(canConstruct("aa", "aab")); // -> true
    }
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] freqs = new int[26];
        for (int i = 0; i < magazine.length(); ++i) {
            char c = magazine.charAt(i);
            ++freqs[c-'a'];
        }
        for (int i = 0; i < ransomNote.length(); ++i) {
            char c = ransomNote.charAt(i);
            if (--freqs[c-'a'] < 0)
                return false;
        }
        return true;
    }
}
