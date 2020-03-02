package com.rainz;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Yu on 1/27/2015.
 */
public class LongestValidParentheses {
    public static void test(String args[]) {
//        System.out.println(longestValidParentheses("()")); // 2
//        System.out.println(longestValidParentheses("(()")); // 2
//        System.out.println(longestValidParentheses("()(()")); // 2
//        System.out.println(longestValidParentheses("()()")); // 4
//        System.out.println(longestValidParentheses(")()())")); // 4
//        System.out.println(longestValidParentheses("(((())))")); // 8
//        System.out.println(longestValidParentheses(")))((()))()")); // 8
//        System.out.println(longestValidParentheses(")()()))))((()))())))()")); // 8
//        System.out.println(longestValidParentheses(")()()}}((()))())))()))(((())))((()))")); // 14
//        System.out.println(longestValidParentheses("(())()(()((")); // 6
        System.out.println(longestValidParentheses("(()()")); // 4
    }

    public static int longestValidParentheses(String s) {
        return longestValidParenthesesNew(s);
    }

    public static int longestValidParenthesesNew(String s) {
        int ans = 0;
        int start = 0;
        Stack<Integer> indices = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(')
                indices.push(i);
            else {
                // Here, c is ')'
                if (indices.isEmpty()) {
                    /*
                     * See diagram below.
                     * ()())(...
                     *     i^
                     * Here at i, the extra ')' "terminated" the previous valid parentheses block.
                     * So future valid blocks will have to start at i+1 (marked by '^' in the diagram)
                     */
                    start = i + 1;
                    continue;
                }
                int len = 0;
                indices.pop(); // pop the matching '('
                if (indices.isEmpty()) {
                    /*
                     * See diagram below
                     * ()())()()...
                     *      ^  i
                     * At i, all '('s have been matched.
                     * So valid parentheses will be between i and start (marked by '^')
                     */
                    len = i - start + 1;
                } else {
                    /*
                     * See diagram below
                     * ()())()()((()()...
                     *          ^*   i
                     * At i, not all '('s have been matched.
                     * So valid parentheses block will be from i to (but not including) prev unmatched '(' (marked by '*').
                     * Here start is again marked by '^'
                     */
                    len = i - indices.peek(); // len from i to outer level
                }
                if (len > ans)
                    ans = len;
            }
        }
        return ans;
    }
    public static int longestValidParenthesesOldPass(String s) {
        int longest = 0;
        // Use positive numbers for lengths, negative for count of '('
        Stack<Integer> stk = new Stack<Integer>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                if (stk.empty() || stk.peek().intValue() > 0) {
                    // The previous entry is not a count of '(', so add a new one
                    stk.push(-1); // -1 since count is negative
                    continue;
                }
                // Increment '(' count (-1 since count is negative)
                Integer openCount = stk.pop();
                stk.push(openCount.intValue() - 1);
            } else {
                // Handle ')'
                int validLen = 0;
                Integer top = null;
                if (!stk.empty()) {
                    top = stk.pop();
                    // If top of stack is a length, record it and pop the next one (should be a count)
                    if (top.intValue() > 0) {
                        validLen = top.intValue();
                        top = stk.empty() ? null : stk.pop(); // next one, if present, should be a count
                    }
                }
                if (top == null) {
                    // No '(' to match this ')', so reset
                    stk.clear();
                    continue;
                }
                // Now top should point to a '(' count
                if (top.intValue() < -1) {
                    // Here we have multiple '('s, match one with current ')', so decrement count and push it back
                    stk.push(top.intValue() + 1); // +1 because count is negative
                } else {
                    // Here we have exactly one '('. It will be popped and matched.
                    // So we merge current length with previous length, if one is present
                    if (!stk.empty())
                        validLen += stk.pop().intValue();
                }
                validLen += 2; // add '(' and ')'
                stk.push(validLen);
                if (validLen > longest)
                    longest = validLen;
            }
        }

        return longest;
    }

}
