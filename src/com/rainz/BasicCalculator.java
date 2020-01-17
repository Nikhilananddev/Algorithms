package com.rainz;

import java.util.Stack;

/*
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 */
public class BasicCalculator {
    public static void test(String args[]) {
        System.out.println(calculate("1 + 1"));
        System.out.println(calculate(" 2-1 + 2 "));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    private static int computeOrAssign(char op, int num1, int num2) {
        int result = -1;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            default: // op doesn't exist, just return num2
                result = num2;
                break;
        }
        return result;
    }

    // Return result and new index
    public static int[] helper(String s, int start) {
        String expr = "(" + s + ")"; // avoid having to repeat code after the loop
        char lastOp = '\0';
        int result = 0;
        int i = start;
        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (c == '(') {
                int[] ret = helper(s, i+1);
                i = ret[1];
                result = computeOrAssign(lastOp, result, ret[0]);
            } else if (c == ')') {
                break;
            } else if (Character.isDigit(c)) {
                int num = 0;
                do {
                    int d = c - '0';
                    num = num*10 + c - '0';
                    ++i;
                    c = i < expr.length() ? expr.charAt(i) : '\0';
                } while (Character.isDigit(c));
                --i;
                result = computeOrAssign(lastOp, result, num);
            } else if (c == '+' || c == '-') {
                lastOp = c;
            }
            ++i;
        }
        int[] ret = {result, i};
        return ret;
    }

    public static int calculate(String s) {
        return helper(s, 0)[0];
    }
}
