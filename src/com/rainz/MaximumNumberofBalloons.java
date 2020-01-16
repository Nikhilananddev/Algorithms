package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 */
public class MaximumNumberofBalloons {
    public static void test(String args[]) {
        System.out.println(maxNumberOfBalloons("nlaebolko"));
        System.out.println(maxNumberOfBalloons("loonbalxballpoon"));
        System.out.println(maxNumberOfBalloons("leetcode"));
    }

    public static int maxNumberOfBalloons(String text) {
        if (text.length() == 0)
            return 0;
        List<Character> targetChars = new ArrayList<>();
        int[] targetFreqs = new int[26];
        final String targetStr = "balloon";
        for (int i = 0; i < targetStr.length(); ++i) {
            char c = targetStr.charAt(i);
            if (targetFreqs[c-'a']++ == 0)
                targetChars.add(c);
        }

        int[] textFreqs = new int[26];
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            ++textFreqs[c - 'a'];
        }
        int minFreq = Integer.MAX_VALUE;
        for (char c: targetChars) {
            int freq = textFreqs[c-'a'] / targetFreqs[c-'a'];
            if (freq < minFreq)
                minFreq = freq;
        }
        return minFreq;
    }
}
