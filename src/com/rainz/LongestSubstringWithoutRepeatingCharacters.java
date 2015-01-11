package com.rainz;

/**
 * Created by Yu on 1/11/2015.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void test(String args[]) {
        String testString = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(testString));
        testString = "aaaaaa";
        System.out.println(lengthOfLongestSubstring(testString));
        testString = "abcxyz";
        System.out.println(lengthOfLongestSubstring(testString));
        testString = "a";
        System.out.println(lengthOfLongestSubstring(testString));
        testString = "";
        System.out.println(lengthOfLongestSubstring(testString));
        testString = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco";
        System.out.println(lengthOfLongestSubstring(testString));
    }

    public static int lengthOfLongestSubstring(String s) {
        boolean[] lookup = new boolean[256];
        int maxLen = 0;
        int startIdx = 0, endIdx = 0;
        while (endIdx < s.length()) {
            // Every time we get here, [startIdx, endIdx-1] has no repeating chars
            char curr = s.charAt(endIdx);
            if (!lookup[curr]) {
                lookup[curr] = true;
                // There is still no repeat. Check if this is the longest answer
                int len = endIdx - startIdx + 1;
                if (len > maxLen)
                    maxLen = len;
            }
            else {
                // We got a repeat, must contract window until the previous instance is gone
                while (s.charAt(startIdx) != curr) {
                    lookup[s.charAt(startIdx)] = false; // clear the bit for each removed char
                    ++startIdx;
                }
                ++startIdx; // now we have gotten rid of the previous instance of "curr"
            }
            // We are again without repeating chars. Expand window for the next iteration.
            ++endIdx;
        }
        return maxLen;
    }
}
