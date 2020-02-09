package com.rainz;

/*
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 */
public class FirstUniqueCharacterinaString {
    public static void test(String args[]) {
        System.out.println(firstUniqChar("leetcode"));
        System.out.println(firstUniqChar("loveleetcode"));
        System.out.println(firstUniqChar("leetcodeedocteel"));
    }

    public static int firstUniqChar(String s) {
        int[] freqs = new int[26];

        for (int i = 0; i < s.length(); ++i)
            ++freqs[s.charAt(i)-'a'];
        for (int i = 0; i < s.length(); ++i) {
            if (freqs[s.charAt(i)-'a'] == 1)
                return i;
        }
        return -1;
    }
}
