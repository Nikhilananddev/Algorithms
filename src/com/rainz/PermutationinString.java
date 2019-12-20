package com.rainz;

import java.util.Arrays;

public class PermutationinString {
    public static void test(String args[]) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));
        System.out.println(checkInclusion("rmqqh", "nrsqrqhrymf"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        int[] allFreqs = new int[256];
        int allCount = 0;
        for (int i = 0; i < s1.length(); ++i) {
            ++allFreqs[s1.charAt(i)];
            ++allCount;
        }

        int start = 0;
        int end = 0;
        int total = 0;
        int[] freqs = new int[256];
        while (end < s2.length()) {
            ++end;
            char lastChar = s2.charAt(end-1);
            if (allFreqs[lastChar] == 0) {
                // Found unknown char, reset windows
                start = end;
                Arrays.fill(freqs, 0);
                total = 0;
                continue;
            }
            ++freqs[lastChar];
            if (freqs[lastChar] <= allFreqs[lastChar])
                ++total;

            int winLen = end - start;
            // We first expand the window to match s1.length()
            if (winLen < s1.length())
                continue;
            if (winLen > s1.length()) {
                char firstChar = s2.charAt(start);
                --freqs[firstChar];
                if (freqs[firstChar] < allFreqs[firstChar])
                    --total;
                ++start; // keep window length at s1.length()
            }
            // if winLen == s1.length(), window contains all valid letters at potentially different count
            if (total == allCount)
                return true;
        }
        return false;
    }
}
