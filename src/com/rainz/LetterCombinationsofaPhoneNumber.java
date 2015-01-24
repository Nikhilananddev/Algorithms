package com.rainz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu on 1/24/2015.
 */
public class LetterCombinationsofaPhoneNumber {

    public static void test(String args[]) {
        String test = "23";
        List<String> result = letterCombinations((test));
        for (String s: result) {
            System.out.println(s);
        }
    }

    protected static String[] _letters = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private static void helper(String digits, int idx, StringBuilder blder, List<String> answer) {
        if (idx == digits.length()) {
            answer.add(blder.toString());
            return;
        }
        int d = digits.charAt(idx) - '0';
        String combo = _letters[d];
        for (int i = 0; i < combo.length(); ++i) {
            char c = combo.charAt(i);
            blder.append(c);
            helper(digits, idx+1, blder, answer);
            blder.deleteCharAt(idx);
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> answer = new ArrayList<String>();
        StringBuilder blder  = new StringBuilder();
        helper(digits, 0, blder, answer);
        return answer;
    }
}
