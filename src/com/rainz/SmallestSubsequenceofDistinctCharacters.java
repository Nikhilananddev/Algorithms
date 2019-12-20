package com.rainz;

/*
 * Return the lexicographically smallest subsequence of text that contains all the distinct characters of text exactly once.
 */
public class SmallestSubsequenceofDistinctCharacters {
    public static void test(String args[]) {
        System.out.println(smallestSubsequence("cdadabcc"));
        System.out.println(smallestSubsequence("abcd"));
        System.out.println(smallestSubsequence("ecbacba"));
        System.out.println(smallestSubsequence("leetcode"));
    }

    public static String smallestSubsequence(String text) {
        int[] lastIndices = new int[26];
        for (int i = 0; i < text.length(); ++i) {
            lastIndices[text.charAt(i)-'a'] = i;
        }

        boolean[] used = new boolean[26];
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (used[c-'a'])
                continue;
            int idx = sb.length() - 1;
            /* if current char is smaller than last char used, and
               there are more last chars later, replace last char with current
             */
            while (idx >= 0 && c < sb.charAt(idx) && i < lastIndices[sb.charAt(idx)-'a']) {
                used[sb.charAt(idx)-'a'] = false;
                --idx;
            }
            sb.delete(idx+1, sb.length());
            sb.append(c);
            used[c-'a'] = true;
        }
        return sb.toString();
    }
}
