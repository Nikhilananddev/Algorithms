package com.rainz;

/*
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
 */
public class VerifyinganAlienDictionary {
    public static void test(String args[]) {
        String[] words1 = {"hello","leetcode"};
        String order1 = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(isAlienSorted(words1, order1));
        String[] words2 = {"word","world","row"};
        String order2 = "worldabcefghijkmnpqstuvxyz";
        System.out.println(isAlienSorted(words2, order2));
        String[] words3 = {"apple","app"};
        String order3 = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(isAlienSorted(words3, order3));
    }
    public static boolean isAlienSorted(String[] words, String order) {
        String prev = null;
        char[] mapping = new char[26];
        for (int i = 0; i < order.length(); ++i)
            mapping[order.charAt(i)-'a'] = (char)('a' + i);
        for (String w: words) {
            // Decode this word
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < w.length(); ++i) {
                char c = w.charAt(i);
                sb.append(mapping[c-'a']);
            }
            if (prev != null) {
                if (sb.toString().compareTo(prev) < 0)
                    return false;
            }
            prev = sb.toString();
        }
        return true;
    }
}
