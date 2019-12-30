package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public static void test(String args[]) {
        Main.printList2D(partition("aab"));
    }

    private static void helper(String s, int start, List<String> sol, List<List<String>> ans) {
        if (start >= s.length()) {
            List<String> solution = new ArrayList<>();
            solution.addAll(sol);
            ans.add(solution);
            return;
        }
        for (int i = start; i < s.length(); ++i) {
            // Determine if this is a palindrome
            int left = start;
            int right = i;
            while (left < right && s.charAt(left) == s.charAt(right)) {
                ++left;
                --right;
            }
            if (left < right)
                continue; // not a palindrome
            sol.add(s.substring(start, i+1));
            helper(s, i+1, sol, ans);
            sol.remove(sol.size()-1);
        }
        return;
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> sol = new ArrayList<>();
        helper(s, 0, sol, ans);
        return ans;
    }
}
