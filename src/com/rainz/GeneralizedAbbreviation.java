package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Write a function to generate the generalized abbreviations of a word.
 * Note: The order of the output does not matter.
 */
public class GeneralizedAbbreviation {
    public static void test(String args[]) {
        System.out.println(generateAbbreviations("word"));
    }

    private static void helper(String word, int start, StringBuilder sb, List<String> ans) {
        if (start >= word.length()) {
            ans.add(sb.toString());
            return;
        }
        if (sb.length() == 0 || !Character.isDigit(sb.charAt(sb.length()-1))) {
            // Current abbr doesn't end with digit, so we can use numbers here
            for (int i = 1; start + i - 1 < word.length(); ++i) {
                int sbLen = sb.length();
                sb.append(i);
                helper(word, start+i, sb, ans);
                sb.setLength(sbLen);
            }
        }
        // Try letter
        sb.append(word.charAt(start));
        helper(word, start+1, sb, ans);
        sb.deleteCharAt(sb.length()-1);
    }

    public static List<String> generateAbbreviations(String word) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(word, 0, sb, ans);
        return ans;
    }
}
