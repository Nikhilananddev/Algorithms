package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 */

public class DifferentWaystoAddParentheses {
    public static void test(String args[]) {
        Main.printList(diffWaysToCompute("2-1-1"));
        Main.printList(diffWaysToCompute("2*3-4*5"));
        Main.printList(diffWaysToCompute("10+5"));
    }

    private static List<Integer> helper(List<Integer> tokens, int start, int end) {
        List<Integer> results = new ArrayList<>();
        // start and end-1 always point at two numbers
        if (start == end)
            return results;
        if (end - start < 3) {
            results.add(tokens.get(start)); // no operator
            return results;
        }
        for (int i = start+1; i < end; i += 2) {
            // Loop through all operators
            char op = (char)tokens.get(i).intValue();
            List<Integer> left = helper(tokens, start, i);
            List<Integer> right = helper(tokens, i+1, end);
            for (int l: left) {
                for (int r : right) {
                    int val = op == '+' ? l+r : (op == '-' ? l-r : l*r);
                    results.add(val);
                }
            }
        }
        return results;
    }

    public static List<Integer> diffWaysToCompute(String input) {
        List<Integer> tokens = new ArrayList<>();
        // Even items are numbers, odd items are operators
        Integer num = null;
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (c == '+' | c == '-' || c == '*') {
                if (num != null)
                    tokens.add(num);
                tokens.add((int)c);
                num = null;
            }
            else {
                if (num == null)
                    num = 0;
                num = num*10 + c - '0';
            }
        }
        tokens.add(num); // add the last num
        return helper(tokens, 0, tokens.size());
    }
}
