package com.rainz;

import java.util.Stack;

/*
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
 *
 * Note: in actual test cases there are negative numbers!!!
 */
public class BasicCalculatorIII {
    public static void test(String args[]) {
        System.out.println(calculate("1 + 1"));
        System.out.println(calculate(" 6-4 / 2 "));
        System.out.println(calculate("2*(5+5*2)/3+(6/2+8)"));
        System.out.println(calculate("(2+6* 3+5- (3*14/7+2)*5)+3"));
        System.out.println(calculate("-1+4*3/3/3"));
    }

    private static int compute(char op, int num1, int num2) {
        int result = -1;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        return result;
    }

    // Return result and new index
    public static int[] helper(String s, int start) {
        Stack<Integer> numStk = new Stack<>();
        Stack<Character> opStk = new Stack<>();
        int[] precedence = new int[256];
        precedence['+'] = 1;
        precedence['-'] = 1;
        precedence['*'] = 2;
        precedence['/'] = 2;
        int i = start;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '(') {
                int[] ret = helper(s, i+1);
                i = ret[1];
                numStk.push(ret[0]);
            } else if (c == ')') {
                break;
            } else if (Character.isDigit(c)) {
                int num = 0;
                do {
                    int d = c - '0';
                    num = num*10 + c - '0';
                    ++i;
                    c = i < s.length() ? s.charAt(i) : '\0';
                } while (Character.isDigit(c));
                --i;
                numStk.push(num);
            } else if (c != ' ') {
                // c is one of the operators
                while (!opStk.isEmpty() && precedence[opStk.peek()] >= precedence[c]) {
                    char op = opStk.pop();
                    int num2 = numStk.pop();
                    int num1;
                    if (op == '-' && numStk.isEmpty())
                        num1 = 0;
                    else
                        num1 = numStk.pop();
                    numStk.push(compute(op, num1, num2));
                }
                opStk.push(c);
            }
            ++i;
        }
        while (!opStk.isEmpty()) {
            char op = opStk.pop();
            int num2 = numStk.pop();
            int num1;
            if (op == '-' && numStk.isEmpty())
                num1 = 0;
            else
                num1 = numStk.pop();
            numStk.push(compute(op, num1, num2));
        }
        int[] ret = {numStk.peek(), i};
        return ret;
    }

    public static int calculate(String s) {
        return helper(s, 0)[0];
    }
}
