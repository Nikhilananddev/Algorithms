package com.rainz;

/*
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 */

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    public static void test(String args[]) {
        System.out.println(addOperators("123", 6));
        System.out.println(addOperators("232", 8));
        System.out.println(addOperators("105", 5));
        System.out.println(addOperators("00", 0));
        System.out.println(addOperators("3456237490", 9191));

    }

    /*
     * Idea: use DFS to search all possible expressions.
     * For each position i in the string
     * - Try all possible numbers starting from i (up to max int)
     * - For each number curr, try applying +, -, * to curr.
     *   - '-' is same as '+' with a negative "curr"
     *   - '*' is trickier. Example: 1 + 2*3*4
     *     - When curr=3, we need to delete 2 and add 2*3; when curr=4, we delete 2*3 and add 2*3*4
     *     - So we keep track of the last n (n>=1) number(s) multiplied together (we call it a "term")
     *     - When we see '*', we subtract the currTerm, multiple it with curr, then add it back.
     */
    private static void helper(String num, int start, int target, long currTerm, long curSum, StringBuilder sb, List<String> res) {
        if (start >= num.length() && curSum == target) {
            res.add(sb.toString());
            return;
        }
        // First, try every length for current number
        for (int end = start+1; end <= num.length(); ++end) {
            String cur = num.substring(start, end);
            if (cur.length() > 1 && cur.charAt(0) == '0')
                break; // can't allow leading 0's; so once cur has more than 1 letter, stop right here.
            int curVal = 0;
            try {
                curVal = Integer.parseInt(cur);
            } catch (NumberFormatException e) {
                break; // can happen if cur exceeds max int value
            }
            int nextStart = end;
            int sbLen = sb.length();
            // For each number, try + - *
            if (sb.length() > 0) {
                sb.append('+'); sb.append(cur);
                helper(num, nextStart, target, curVal, curSum + curVal, sb, res);
                sb.setLength(sbLen);
                sb.append('-'); sb.append(cur);
                helper(num, nextStart, target, -curVal, curSum - curVal, sb, res);
                sb.setLength(sbLen);
                sb.append('*'); sb.append(cur);
                helper(num, nextStart, target, currTerm * curVal, (curSum - currTerm) + currTerm * curVal, sb, res);
                sb.setLength(sbLen);
            } else {
                // First number
                sb.append(cur);
                helper(num, nextStart, target, curVal, curVal, sb, res);
                sb.setLength(sbLen);
            }
        }
    }

    public static List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(num, 0, target, 0, 0, sb, ans);
        return ans;
    }
}
