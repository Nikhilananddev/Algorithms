package com.rainz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu on 1/24/2015.
 */
public class GenerateParentheses {
    public static void test(String args[]) {
        List<String> result = generateParenthesis(3);
        for (String s: result)
            System.out.println(s);
    }

    // nOpen is # open parenthesis left, nClose is # close parenthesis left. nOpen <= nClose
    protected static void helper(StringBuilder blder, int nOpen, int nClose, List<String> answer)  {
        if (nClose == 0) {
            answer.add(blder.toString());
            return;
        }
        if (nOpen > 0) {
            blder.append('(');
            helper(blder, nOpen-1, nClose, answer);
            blder.deleteCharAt(blder.length() - 1);
        }
        if (nClose > nOpen) {
            blder.append(')');
            helper(blder, nOpen, nClose-1, answer);
            blder.deleteCharAt(blder.length() - 1);
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<String>();
        if (n == 0)
            return answer;
        StringBuilder blder = new StringBuilder();
        helper(blder, n, n, answer);
        return answer;
    }
}
