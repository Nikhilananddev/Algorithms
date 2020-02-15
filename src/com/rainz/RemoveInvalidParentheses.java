package com.rainz;

import java.util.*;

/*
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 */
public class RemoveInvalidParentheses {
    public static void test(String args[]) {
        System.out.println(removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses("(a)())()"));
        System.out.println(removeInvalidParentheses(")("));
    }

    private static boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch == '(')
                ++count;
            else if (ch == ')')
                --count;
            if (count < 0)
                return false;
        }
        return count == 0;
    }

    /*
     * BFS solution:
     * First test s, then remove 1 parenthesis and test strings with s.length-1, then s.length-2, s.length-3, etc
     */
    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> workQ = new LinkedList<>();
        workQ.add(s);
        while (!workQ.isEmpty()) {
            String curr = workQ.remove();
            if (!ans.isEmpty() && curr.length() < ans.get(0).length())
                break; // stop here. don't process shorter strings
            if (visited.contains(curr))
                continue;
            visited.add(curr);
            if (isValid(curr)) {
                ans.add(curr);
            }
            if (!ans.isEmpty())
                continue; // found at least one valid string, so don't queue any shorter ones
            for (int i = 0; i < curr.length(); ++i) {
                char ch = curr.charAt(i);
                if (ch != '(' && ch != ')')
                    continue;
                String next = curr.substring(0, i) + curr.substring(i + 1);
                workQ.add(next);
            }
        }
        return ans;
    }
}
