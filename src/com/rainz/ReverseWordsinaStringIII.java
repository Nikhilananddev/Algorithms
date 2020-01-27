package com.rainz;

/*
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 */
public class ReverseWordsinaStringIII {
    public static void test(String args[]) {
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }
    public static String reverseWords(String s) {
        char[] cs = s.toCharArray();
        int start = -1;
        int L = s.length();
        for (int i = 0; i <= L; ++i) {
            char c = i < L ? cs[i] : ' ';
            if (c != ' ') {
                if (start < 0)
                    start = i;
            } else {
                if (start >= 0) {
                    int end = i - 1;
                    while (start < end) {
                        char tmp = cs[start];
                        cs[start] = cs[end];
                        cs[end] = tmp;
                        ++start; --end;
                    }
                }
                start = -1;
            }
        }
        return new String(cs);
    }
}
