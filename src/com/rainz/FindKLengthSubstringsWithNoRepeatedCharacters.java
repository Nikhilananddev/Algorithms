package com.rainz;

/*
 * Given a string S, return the number of substrings of length K with no repeated characters.
 */
public class FindKLengthSubstringsWithNoRepeatedCharacters {
    public static void test(String args[]) {
        System.out.println(numKLenSubstrNoRepeats("havefunonleetcode", 5));
        System.out.println(numKLenSubstrNoRepeats("home", 5));
    }

    public static int numKLenSubstrNoRepeats(String S, int K) {
        if (S.length() < K)
            return 0;
        int ans = 0;
        int startIdx = 0, endIdx = 0; // endIdx is exclusive
        int[] freqs = new int[26];
        int numDups = 0;
        while (endIdx < S.length()) {
            ++endIdx;
            char c = S.charAt(endIdx-1);
            if (++freqs[c-'a'] == 2)
                ++numDups;
            if (endIdx - startIdx > K) {
                c = S.charAt(startIdx);
                if (--freqs[c-'a'] == 1)
                    --numDups;
                ++startIdx;
            }
            if (endIdx - startIdx == K && numDups == 0)
                ++ans;
        }
        return ans;
    }
}
