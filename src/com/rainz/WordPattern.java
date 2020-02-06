package com.rainz;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 */
public class WordPattern {
    public static void test(String args[]) {
        System.out.println(wordPattern("abba", "dog cat cat dog")); // true
        System.out.println(wordPattern("abba", "dog cat cat fish")); // false
        System.out.println(wordPattern("aaaa", "dog cat cat dog")); // false
        System.out.println(wordPattern("abba", "dog dog dog dog")); // false
    }
    public static boolean wordPattern(String pattern, String str) {
        String[] parts = str.split(" ");
        int L = parts.length;
        if (pattern.length() != L)
            return false;
        Map<Character, String> p2s = new HashMap<>();
        Map<String, Character> s2p = new HashMap<>();
        for (int i = 0; i < L; ++i) {
            char p = pattern.charAt(i);
            String s = parts[i];
            // Check pattern-to-string
            String ms = p2s.get(p);
            if (ms == null)
                p2s.put(p, s);
            else if (!ms.equals(s))
                return false;
            // Check string-to-pattern
            Character mc = s2p.get(s);
            if (mc == null)
                s2p.put(s, p);
            else if (mc.charValue() != p)
                return false;
        }
        return true;
    }
}
