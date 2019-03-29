package com.rainz;

public class MinimumAddToMakeParenthesesValid {
    public static void test(String args[]) {
        System.out.println(minimumAddToMakeParenthesesValid("())"));
        System.out.println(minimumAddToMakeParenthesesValid("((("));
        System.out.println(minimumAddToMakeParenthesesValid("()"));
        System.out.println(minimumAddToMakeParenthesesValid("()))(("));
    }

    public static int minimumAddToMakeParenthesesValid(String S) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < S.length(); ++i) {
            char c = S.charAt(i);
            if (c == '(')
                ++left;
            else if (left > 0)
                --left;
            else
                ++right;
        }
        return left + right;
    }
}
