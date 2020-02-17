package com.rainz;

/*
 * Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.
 * Return the minimum number of steps to make t an anagram of s.
 */
public class MinimumNumberofStepstoMakeTwoStringsAnagram {
    public static void test(String args[]) {
        System.out.println(minSteps("bab", "aba"));
        System.out.println(minSteps("leetcode", "practice"));
        System.out.println(minSteps("anagram", "mangaar"));
        System.out.println(minSteps("xxyyzz", "xxyyzz"));
        System.out.println(minSteps("friend", "family"));
    }

    private static void buildFreq(String s, int[] freq) {
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            ++freq[c-'a'];
        }
    }

    public static int minSteps(String s, String t) {
        int[] sFreq = new int[26], tFreq = new int[26];
        buildFreq(s, sFreq);
        buildFreq(t, tFreq);
        int moreS = 0, moreT = 0;
        for (int i = 0; i < sFreq.length; ++i) {
            int diff = sFreq[i] - tFreq[i];
            if (diff > 0)
                moreS += diff;
            else
                moreT -= diff;
        }
        int ans = Math.min(moreS, moreT);
        ans += Math.abs(moreS - moreT);
        return ans;
    }
}
