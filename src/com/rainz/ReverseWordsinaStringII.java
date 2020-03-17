package com.rainz;

public class ReverseWordsinaStringII {
    public static void test(String args[]) {
        char[] input1 = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        reverseWords(input1);
        Main.printArray(input1);
    }

    private static void reverse(char[] s, int start, int end) {
        while (start < end) {
            char c = s[start];
            s[start] = s[end];
            s[end] = c;
            ++start;
            --end;
        }
    }

    public static void reverseWords(char[] s) {
        int L = s.length;
        reverse(s, 0, L-1);
        int start = 0;
        for (int i = 0; i <= L; ++i) {
            char c = i < L ? s[i] : ' ';
            if (c == ' ') {
                reverse(s, start, i-1);
                start = i+1;
            }
        }
    }
}
