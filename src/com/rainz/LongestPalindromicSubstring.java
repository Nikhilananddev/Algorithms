package com.rainz;

/**
 * Created by Yu on 1/18/2015.
 */
public class LongestPalindromicSubstring {
    public static void test(String args[]) {
        String[] tests = {"a", "asdfj4567654adfj", "asdf123321kd", "asdffdsa", "asddsaiojlcv1234543214224;fjd"};
        for (String s: tests) {
            System.out.println(longestPalindrome(s));
        }
    }

    public static String longestPalindrome(String s) {
        final int strLen = s.length();
        if (strLen == 0)
            return "";
        int maxLeft = 0, maxRight = 0, maxLen = 1;
        for (int i = 0; i < 2*strLen - 1; ++i) {
            int leftIdx, rightIdx;
            if ((i + 1) % 2 == 0) {
                // Element
                leftIdx = i / 2 - 1;
                rightIdx = leftIdx + 2;
            } else {
                // Space in between
                leftIdx = i / 2;
                rightIdx = leftIdx + 1;
            }
            while (leftIdx >=0 && rightIdx < strLen && s.charAt(leftIdx) == s.charAt(rightIdx)) {
                --leftIdx;
                ++rightIdx;
            }
            int len = rightIdx - leftIdx - 2 + 1;
            if (len > maxLen) {
                maxLen = len;
                maxLeft = leftIdx + 1;
                maxRight = rightIdx - 1;
            }
        }
        return s.substring(maxLeft, maxRight + 1);
    }
}
