package com.rainz;

public class LongestSubstringwithAtMostTwoDistinctCharacters {
    public static void test(String args[]) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int ans = 0;
        int start = 0, end = 0; // window is [start, end)
        int distinct = 0;
        int[] freqs = new int[256];

        while (end < s.length()) {
            if (distinct <= 2) {
                // Expand
                ++end;
                char e = s.charAt(end - 1);
                if (++freqs[e] == 1)
                    ++distinct;
            }
            if (distinct <= 2) {
                int len = end - start;
                if (len > ans)
                    ans = len;
                continue;
            }
            // Shrink
            while (distinct > 2) {
                char c = s.charAt(start);
                if (--freqs[c] == 0)
                    --distinct;
                ++start;
            }
        }
        return ans;
    }
}