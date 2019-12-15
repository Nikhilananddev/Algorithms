package com.rainz;

import java.util.Arrays;
import java.util.List;

public class LongestWordinDictionarythroughDeleting {
    public static void test(String args[]) {
        String[] input1 = {"ale","apple","monkey","plea"};
        List<String> dict1 = Arrays.asList(input1);
        System.out.println(findLongestWord("abpcplea", dict1));

        String[] input2 = {"a","b","c"};
        List<String> dict2 = Arrays.asList(input2);
        System.out.println(findLongestWord("abpcplea", dict2));

        String[] input3 = {"ba","ab","a","b"};
        List<String> dict3 = Arrays.asList(input3);
        System.out.println(findLongestWord("bab", dict3));
    }

    public static String findLongestWord(String s, List<String> d) {
        String longest = "";
        for (String w: d) {
            int wIdx = 0;
            for (int sIdx = 0; sIdx < s.length(); ++sIdx) {
                if (s.charAt(sIdx) != w.charAt(wIdx))
                    continue;
                ++wIdx;
                if (wIdx >= w.length())
                    break;
            }
            if (wIdx >= w.length()) {
                if (w.length() > longest.length() ||
                        (w.length() == longest.length() && longest.compareTo(w) > 0))
                    longest = w;
            }
        }
        return longest;
    }
}
