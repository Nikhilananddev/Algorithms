package com.rainz;

import java.util.Stack;

public class MinimumRemovetoMakeValidParentheses {
    public static void test(String args[]) {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(minRemoveToMakeValid("a)b(c)d"));
        System.out.println(minRemoveToMakeValid("))(("));
        System.out.println(minRemoveToMakeValid("(a(b(c)d)"));
    }

    public static String minRemoveToMakeValid(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> leftParen = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                result.append(c);
                leftParen.push(result.length()-1);
            } else if (c == ')') {
                if (leftParen.isEmpty())
                    continue;
                leftParen.pop();
                result.append(c);
            } else
                result.append(c);
        }
        // Don't forget to process remaining "("s
        while (!leftParen.isEmpty()) {
            result.deleteCharAt(leftParen.pop());
        }
        return result.toString();
    }

}
