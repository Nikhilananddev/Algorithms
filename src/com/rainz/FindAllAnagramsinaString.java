package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 */
public class FindAllAnagramsinaString {
    public static void test(String args[]) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }


    public static List<Integer> findAnagrams(String s, String p) {
        int[] allFreqs = new int[256];
        for (int i = 0; i < p.length(); ++i) {
            ++allFreqs[p.charAt(i)];
        }

        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        int total = 0;
        int[] freqs = new int[256];
        while (end < s.length()) {
            ++end;
            char lastChar = s.charAt(end-1);
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
            if (winLen < p.length())
                continue;
            if (winLen > p.length()) {
                char firstChar = s.charAt(start);
                --freqs[firstChar];
                if (freqs[firstChar] < allFreqs[firstChar])
                    --total;
                ++start; // keep window length at s1.length()
            }
            if (total == p.length())
                result.add(start);
        }
        return result;
    }
}
