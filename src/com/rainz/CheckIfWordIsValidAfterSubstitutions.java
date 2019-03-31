package com.rainz;

import java.util.Stack;

public class CheckIfWordIsValidAfterSubstitutions {
    public static void test(String args[]) {
        System.out.println(checkIfWordIsValidAfterSubstitutions("aabcbc")); // true
        System.out.println(checkIfWordIsValidAfterSubstitutions("abcabcababcc")); // true
        System.out.println(checkIfWordIsValidAfterSubstitutions("abccba")); // false
        System.out.println(checkIfWordIsValidAfterSubstitutions("cababc")); // false
        System.out.println(checkIfWordIsValidAfterSubstitutions("aabcbcabc")); // true
    }

    public static boolean checkIfWordIsValidAfterSubstitutions(String S) {
        if (S.length() % 3 != 0)
            return false;
        /*
        - Think of 'a' as '(', 'c' as ')'.
        - 'b' can only follow 'a' or 'c'. But previous 'c' will complete the string and pop the previous "ab"
        - 'c' can only follow 'b' or 'c'. But previous 'c' will complete the string and pop the previous "ab"
         */
        Stack<Character> stk = new Stack<>();
        for (char c: S.toCharArray()) {
            if (c == 'a')
                stk.push(c);
            else if (c == 'b') {
                if (stk.isEmpty() || stk.peek() != 'a')
                    return false;
                stk.push(c);
            } else if (c == 'c') {
                // Pop the previous 'b'
                if (stk.isEmpty() || stk.pop() != 'b')
                    return false;
                // Pop the previous 'a'
                if (stk.isEmpty() || stk.pop() != 'a')
                    return false;
            } else {
                return false;
            }
        }

        return stk.isEmpty();
    }
}
