package com.rainz;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
 */
public class WordPatternII {
    public static void test(String args[]) {
        System.out.println(wordPatternMatch("abab", "redblueredblue")); // true
        System.out.println(wordPatternMatch("aaaa", "asdasdasdasd"));   // true
        System.out.println(wordPatternMatch("aabb", "xyzabcxzyabc"));   // false
    }
    private static boolean helper(String pattern, int pIdx, String str, int sIdx, Map<Character, String> p2s, Map<String, Character> s2p) {
        if (pIdx >= pattern.length() && sIdx >= str.length())
            return true; // both pattern and string reached the end, successful match
        if (pIdx >= pattern.length() || sIdx >= str.length())
            return false; // either pattern or string didn't finish, unsuccessful match
        char p = pattern.charAt(pIdx);
        String matchP = p2s.get(p);
        if (matchP != null) {
            // We've seen this pattern name before, so try matching it with current string
            String sub = str.substring(sIdx, Math.min(str.length(), sIdx+matchP.length()));
            if (matchP.equals(sub) &&
                helper(pattern, pIdx+1, str, sIdx+matchP.length(), p2s, s2p))
                    return true;
        } else {
            // Unknown pattern
            for (int end = sIdx+1; end <= str.length(); ++end) {
                String sub = str.substring(sIdx, end);
                // Since pattern is unknown, check if substring matches a known pattern
                if (!s2p.containsKey(sub)) {
                    // Pattern unknown and substring is also unknown, good.
                    // So we record this new pattern and continue matching
                    p2s.put(p, sub);
                    s2p.put(sub, p);
                    if (helper(pattern, pIdx+1, str, end, p2s, s2p))
                        return true;
                    p2s.remove(p);
                    s2p.remove(sub);
                }
            }
        }
        return false;
    }
    public static boolean wordPatternMatch(String pattern, String str) {
        // Pattern to string mapping
        Map<Character, String> p2s = new HashMap<>();
        // String to pattern mapping
        // Note: this can be replaced by just a set of mapped strings,
        // since we don't care which pattern the string was mapped to.
        Map<String, Character> s2p = new HashMap<>();
        return helper(pattern, 0, str, 0, p2s, s2p);
    }
}
