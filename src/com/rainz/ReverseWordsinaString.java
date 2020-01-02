package com.rainz;

/*
 * Given an input string, reverse the string word by word.
 * Note: reduce multiple spaces between words to a single space. No leading/traling spaces.
 */

public class ReverseWordsinaString {
    public static void test(String args[]) {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("  hello world!  "));
        System.out.println(reverseWords("a good   example"));
    }

    public static String reverseWords(String s) {
        char[] buffer = new char[s.length()];
        int writeIdx = 0;
        int startW = -1;
        for (int i = s.length()-1; i >= 0; --i) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (startW < 0)
                    continue; // ignore extra spaces
                // This ' ' ends a word. Insert a space then the word
                if (writeIdx > 0)
                    buffer[writeIdx++] = ' ';
                for (int j = i + 1; j <= startW; ++j)
                    buffer[writeIdx++] = s.charAt(j);
                startW = -1;
            } else {
                if (startW < 0)
                    startW = i;
            }
        }
        if (startW >= 0) {
            // Don't forget the last word
            if (writeIdx > 0)
                buffer[writeIdx++] = ' ';
            for (int j = 0; j <= startW; ++j)
                buffer[writeIdx++] = s.charAt(j);
        }

        return new String(buffer, 0, writeIdx);
    }
}
