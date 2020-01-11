package com.rainz;

/*
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 * () has score 1
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 */

import java.util.Stack;

public class ScoreofParentheses {
    public static void test(String args[]) {
        System.out.println(scoreOfParentheses("()"));
        System.out.println(scoreOfParentheses("(())"));
        System.out.println(scoreOfParentheses("()()"));
        System.out.println(scoreOfParentheses("(()(()))"));
    }

    public static int scoreOfParentheses(String S) {
        Stack<Integer> stk = new Stack<>();
        int score = 0;
        for (int i = 0; i < S.length(); ++i) {
            char c = S.charAt(i);
            if (c == '(') {
                stk.push(score);
                score = 0;
            } else { // ')'
                if (score == 0)
                    score = 1;
                else
                    score *= 2;
                score += stk.pop();
            }
        }
        return score;
    }
}
