package com.rainz;

/**
 * Created by Yu on 1/24/2015.
 */
public class LongestCommonPrefix {
    public static void test(String args[]) {
        String[] test = {"abcde", "abcdef", "abc", "abcce"};
        System.out.println(longestCommonPrefix(test));
    }
    public static String longestCommonPrefix(String[] strs) {
        int len = 0;
        if (strs.length == 0) {
            return "";
        }

        boolean done = false;
        while (!done) {
            for (String s: strs) {
                if (len >= s.length()) {
                    done = true;
                    break;
                }
                if (s.charAt(len) != strs[0].charAt(len)) {
                    done = true;
                    break;
                }
            }
            if (!done) {
                ++len;
            }
        }
        return strs[0].substring(0, len);
    }
}
