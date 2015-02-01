package com.rainz;

/**
 * Created by Yu on 1/31/2015.
 */
public class MinimumWindowSubstring {
    public static void test(String args[]) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("ADOBECODEBANC", "XYZ"));
        System.out.println(minWindow("XXADOBECODEBANCXXX", "ABC"));
        System.out.println(minWindow("XXADOBECODEBANCXXX", "XXE"));
        System.out.println(minWindow("caae", "cae"));
    }

    public static String minWindow(String S, String T) {
        int[] lookup = new int[256];
        for (int i = 0; i < T.length(); ++i)
            ++lookup[T.charAt(i)];

        int left = 0, right = 0;
        int[] seen = new int[256];
        int count = 0;
        int minLen = Integer.MAX_VALUE, minLeft = -1;
        while (right < S.length()) {
            char cRight = S.charAt(right);
            if (lookup[cRight] == 0) {
                ++right;
                continue;
            }
            // Expand to right
            ++seen[cRight];
            if (seen[cRight] <= lookup[cRight])
                ++count;
            ++right;
            if (count >= T.length()) {
                // Minimize length by shrinking from left
                char cLeft = S.charAt(left);
                while (lookup[cLeft] == 0 || seen[cLeft] > lookup[cLeft]) {
                    if (seen[cLeft] > lookup[cLeft])
                        --seen[cLeft];
                    cLeft = S.charAt(++left);
                }
                int len = right - left;
                if (len < minLen) {
                    minLen = len;
                    minLeft = left;
                }
            }
        }
        if (minLeft < 0)
            return "";
        return S.substring(minLeft, minLeft + minLen);
    }
}
