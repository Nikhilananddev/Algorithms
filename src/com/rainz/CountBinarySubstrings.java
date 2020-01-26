package com.rainz;

/*
 * Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
 * Substrings that occur multiple times are counted the number of times they occur.
 */
public class CountBinarySubstrings {
    public static void test(String args[]) {
        System.out.println(countBinarySubstrings("00110011"));
        System.out.println(countBinarySubstrings("10101"));
    }
    public static int countBinarySubstrings(String s) {
        int lastRun = 0, currRun = 0;
        char lastChar = '\0';
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c != lastChar) {
                lastRun = currRun;
                currRun = 0;
            }
            ++currRun;
            if (lastRun >= currRun)
                ++ans;
            lastChar = c;
        }
        return ans;
    }

}
