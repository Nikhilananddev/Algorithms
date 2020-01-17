package com.rainz;

/*
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 */

import java.util.Stack;

public class BasicCalculatorII {
    public static void test(String args[]) {
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate(" 3/2 "));
        System.out.println(calculate(" 3+5 / 2 "));
        System.out.println(calculate("1+1+1"));
        System.out.println(calculate("1*2-3/4+5*6-7*8+9/10"));
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

    public static int calculate(String s) {
        Stack<Integer> numStk = new Stack<>();
        Stack<Character> opStk = new Stack<>();
        int[] precedence = new int[256];
        precedence['+'] = 1;
        precedence['-'] = 1;
        precedence['*'] = 2;
        precedence['/'] = 2;
        int num = -1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;
            if (c >= '0' && c <= '9') {
                int d = c - '0';
                if (num < 0)
                    num = d;
                else
                    num = num*10 + d;
                continue;
            }
            // Now c is one of the four operators
            if (num >= 0) {
                numStk.push(num);
                num = -1;
            }
            // Note the "while" here
            while (!opStk.isEmpty() && precedence[opStk.peek()] >= precedence[c]) {
                int num2 = numStk.pop();
                int num1 = numStk.pop();
                char op = opStk.pop();
                numStk.push(compute(op, num1, num2));
            }
            opStk.push(c);
        }
        // Push the last number onto stack
        if (num >= 0)
            numStk.push(num);
        // Process what's left in stack
        while (!opStk.isEmpty()) {
            int num2 = numStk.pop();
            int num1 = numStk.pop();
            char op = opStk.pop();
            numStk.push(compute(op, num1, num2));
        }
        return numStk.peek();
    }
}
