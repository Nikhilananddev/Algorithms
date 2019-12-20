package com.rainz;

/*
 * You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s to i-th character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.
 * You are also given an integer maxCost.
 * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of twith a cost less than or equal to maxCost.
 * If there is no substring from s that can be changed to its corresponding substring from t, return 0.
 */
public class GetEqualSubstringsWithinBudget {
    public static void test(String args[]) {
        System.out.println(equalSubstring("abcd", "bcdf", 3));
        System.out.println(equalSubstring("abcd", "cdef", 3));
    }

    public static int equalSubstring(String s, String t, int maxCost) {
        int[] costs = new int[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            costs[i] = s.charAt(i) - t.charAt(i);
            if (costs[i] < 0)
                costs[i] = -costs[i];
        }

        int start = 0;
        int end = 0;
        int totalCost = 0;
        int maxLen = 0;
        while (end < s.length()) {
            ++end;
            totalCost += costs[end-1];
            while (totalCost > maxCost) {
                totalCost -= costs[start++];
            }
            int newLen = end - start;
            if (newLen > maxLen)
                maxLen = newLen;
        }
        return maxLen;
    }

}
