package com.rainz;

import java.util.*;

/*
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
 */
public class PalindromePermutationII {
    public static void test(String args[]) {
        System.out.println(generatePalindromes("aabb"));
        System.out.println(generatePalindromes("abc"));
        System.out.println(generatePalindromes("a"));
        System.out.println(generatePalindromes("aab"));
        System.out.println(generatePalindromes("aabbaabb"));
        System.out.println(generatePalindromes("aabbaabbc"));
    }

    private static void perm(List<Character> letters, int start, char oddChar, List<String> ans) {
        if (start >= letters.size()) {
            StringBuilder sb = new StringBuilder();
            for (char c: letters)
                sb.append(c);
            StringBuilder rsb = new StringBuilder(sb);
            rsb.reverse();
            StringBuilder palSb = new StringBuilder();
            palSb.append(sb);
            if (oddChar != '\0')
                palSb.append(oddChar);
            palSb.append(rsb);
            if (palSb.length() > 0) // prevent empty string
                ans.add(palSb.toString());
            return;
        }
        char startChar = letters.get(start);
        Set<Character> seen = new HashSet<>();
        for (int i = start; i < letters.size(); ++i) {
            char c = letters.get(i);
            if (seen.contains(c))
                continue;
            seen.add(c);
            letters.set(start, c);
            letters.set(i, startChar);
            perm(letters, start+1, oddChar, ans);
            letters.set(start, startChar);
            letters.set(i, c);
        }
    }
    public static List<String> generatePalindromes(String s) {
        List<String> ans = new ArrayList<>();
        Map<Character, Integer> freqs = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            freqs.put(c, freqs.getOrDefault(c, 0)+1);
        }
        char oddChar = '\0';
        int oddCharFreq = 0;
        List<Character> letters = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry: freqs.entrySet()) {
            char c = entry.getKey();
            int cnt = entry.getValue();
            if (cnt % 2 == 1) {
                if (oddChar == '\0') {
                    oddChar = c;
                    oddCharFreq = cnt;
                } else {
                    return ans; // multiple odd chars, return empty list
                }
            }
            for (int i = 0; i < cnt/2; ++i)
                letters.add(c);
        }
        // Remove 1 odd char
        if (oddCharFreq == 1)
            freqs.remove(oddChar);
        else
            freqs.put(oddChar, oddCharFreq-1);
        perm(letters, 0, oddChar, ans);
        return ans;
    }
}
