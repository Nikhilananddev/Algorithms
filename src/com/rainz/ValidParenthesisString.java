package com.rainz;

public class ValidParenthesisString {
    public static void test(String args[]) {
        System.out.println(checkValidString("()"));
        System.out.println(checkValidString("(*)"));
        System.out.println(checkValidString("(*))"));

    }

    private static boolean helper(String s, int start, int score)
    {
        for (; start < s.length() && s.charAt(start) != '*'; ++start) {
            if (s.charAt(start) == '(')
                ++score;
            else {
                --score;
                if (score < 0)
                    return false;
            }
        }
        if (start < s.length()) {
            // start points to *
            boolean ans = helper(s, start+1, score); // try ""
            if (ans)
                return true;
            ans = helper(s, start+1, score+1); // try "("
            if (ans)
                return true;
            --score;
            if (score < 0)
                return false;
            return helper(s, start+1, score);

        }
        return (score == 0);
    }

    public static boolean checkValidString(String s) {
        return helper(s, 0, 0);
    }
}
