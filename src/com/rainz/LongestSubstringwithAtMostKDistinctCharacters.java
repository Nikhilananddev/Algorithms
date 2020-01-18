package com.rainz;

/*
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 */
public class LongestSubstringwithAtMostKDistinctCharacters {
    public static void test(String args[]) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));
        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1));
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0)
            return 0;
        int[] freqs = new int[256];
        int numDistinct = 0;
        int start = 0, end = 0;
        int ans = 0;
        while (end < s.length()) {
            // Expand window
            ++end;
            char c = s.charAt(end-1);
            if (++freqs[c] == 1)
                ++numDistinct;
            // Shrink window to ensure # distinct chars <= k
            while (numDistinct > k) {
                c = s.charAt(start);
                if (--freqs[c] == 0)
                    --numDistinct;
                ++start;
            }
            if (end - start > ans)
                ans = end - start;
        }
        return ans;
    }

}
