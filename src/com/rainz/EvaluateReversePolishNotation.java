package com.rainz;

import java.util.Stack;

public class EvaluateReversePolishNotation {
    public static void test(String args[]) {
        String[] input1 = {"2", "1", "+", "3", "*"};
        System.out.println(evalRPN(input1));
        String[] input2 = {"4", "13", "5", "/", "+"};
        System.out.println(evalRPN(input2));
        String[] input3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(input3));
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stk = new Stack<>();
        for (String tkn: tokens) {
            try {
                stk.push(Integer.parseInt(tkn));
                continue;
            } catch (NumberFormatException e) {

            }
            char c = tkn.charAt(0);
            int num2 = stk.pop();
            int num1 = stk.pop();
            switch (c) {
            case '+':
                stk.push(num1+num2);
                break;
            case '-':
                stk.push(num1-num2);
                break;
            case '*':
                stk.push(num1*num2);
                break;
            case '/':
                stk.push(num1/num2);
                break;
            }
        }
        return stk.pop();
    }
}
