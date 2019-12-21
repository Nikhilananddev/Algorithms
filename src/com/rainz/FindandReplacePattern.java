package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class FindandReplacePattern {
    public static void test(String args[]) {
        String[] input = {"abc", "deq", "mee", "aqq", "dkd", "ccc"};
        Main.printList(findAndReplacePattern(input, "abb"));
    }

    private static String getMapping(String pattern) {
        char id = 'a';
        int len = pattern.length();
        char[] mapping = new char[256];
        char[] mapped = new char[len];
        int idx = 0;
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            if (mapping[c] == 0)
                mapping[c] = id++;
            mapped[idx++] = mapping[c];
        }
        return new String(mapped);
    }

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        String mappedPattern = getMapping(pattern);
        List<String> result = new ArrayList<>();
        for (String w : words) {
            String m = getMapping(w);
            if (m.equals(mappedPattern))
                result.add(w);
        }
        return result;
    }
}
