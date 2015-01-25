package com.rainz;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Yu on 1/24/2015.
 */
public class ValidParentheses {
    public static void test(String args[]) {
        System.out.println(isValid("{{[]}"));
        System.out.println(isValid("{({[]})}"));
        System.out.println(isValid("{({[}])}"));
    }

    public static boolean isValid(String s) {
        char[] match = new char[256];
        match[')'] = '(';
        match['}'] = '{';
        match[']'] = '[';
        Stack<Character> stk = new Stack<Character>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '{':
                case '[':
                    stk.push(c);
                    break;
                case ')':
                case '}':
                case ']':
                    if (stk.empty())
                        return false;
                    char open = stk.pop();
                    if (open != match[c])
                        return false;
                    break;
                default:
                    return false;
            }
        }
        return stk.empty();
    }
}
