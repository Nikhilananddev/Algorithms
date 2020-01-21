package com.rainz;

/*
 * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
 * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 * Now say a word a from A is universal if for every b in B, b is a subset of a.
 * Return a list of all universal words in A.  You can return the words in any order.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSubsets {
    public static void test(String args[]) {
        String[] A1 = {"amazon","apple","facebook","google","leetcode"}, B1 = {"e","o"};
        System.out.println(wordSubsets(A1, B1));
        String[] A2 = {"amazon","apple","facebook","google","leetcode"}, B2 = {"l","e"};
        System.out.println(wordSubsets(A2, B2));
        String[] A3 = {"amazon","apple","facebook","google","leetcode"}, B3 = {"e","oo"};
        System.out.println(wordSubsets(A3, B3));
        String[] A4 = {"amazon","apple","facebook","google","leetcode"}, B4 = {"lo","eo"};
        System.out.println(wordSubsets(A4, B4));
        String[] A5 = {"amazon","apple","facebook","google","leetcode"}, B5 = {"ec","oc","ceo"};
        System.out.println(wordSubsets(A5, B5));
    }

    private static Map<Character, Integer> buildFreq(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0)+1);
        }
        return freq;
    }

    public static List<String> wordSubsets(String[] A, String[] B) {
        Map<Character, Integer> bFreq = new HashMap<>();
        for (String s: B) {
            Map<Character, Integer> freq = buildFreq(s);
            for(Map.Entry<Character, Integer> e: freq.entrySet()) {
                if (e.getValue() > bFreq.getOrDefault(e.getKey(), 0))
                    bFreq.put(e.getKey(), e.getValue());
            }
        }
        List<String> ans = new ArrayList<>();
        for (String s: A) {
            Map<Character, Integer> freq = buildFreq(s);
            boolean isSubset = true;
            for(Map.Entry<Character, Integer> b: bFreq.entrySet()) {
                if (b.getValue() > freq.getOrDefault(b.getKey(), 0)) {
                    isSubset = false;
                    break;
                }
            }
            if (isSubset)
                ans.add(s);
        }
        return ans;
    }
}
