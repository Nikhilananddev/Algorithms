package com.rainz;

import java.util.*;

public class WordLadder {
    public static void test(String args[]) {
        List<String> d1 = List.of("hot","dot","dog","lot","log","cog");
        System.out.println(ladderLength("hit", "cog", d1));
        List<String> d2 = List.of("hot","dot","dog","lot","log");
        System.out.println(ladderLength("hit", "cog", d2));
    }

    public static int ladderLengthV1(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        dict.addAll(wordList);
        if (!dict.contains(endWord))
            return 0;
        List<String> prev = new ArrayList<>();
        prev.add(beginWord);
        int ladderLen = 1;
        while (!prev.isEmpty()) {
            ++ladderLen;
            List<String> curr = new ArrayList<>();
            for (String w: prev) {
                for (String d: dict) {
                    int diff = 0;
                    for (int i = 0; i < w.length() && diff < 2; ++i)
                        if (w.charAt(i) != d.charAt(i))
                            ++diff;
                    if (diff == 1) {
                        curr.add(d);
                        if (d.equals(endWord))
                            return ladderLen;
                    }
                }
            }
            dict.removeAll(curr);
            prev = curr;
        }
        return 0;
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        dict.addAll(wordList);
        if (!dict.contains(endWord))
            return 0;
        List<String> prev = new ArrayList<>();
        prev.add(beginWord);
        int ladderLen = 1;
        while (!prev.isEmpty()) {
            ++ladderLen;
            List<String> curr = new ArrayList<>();
            for (String w: prev) {
                StringBuilder sb = new StringBuilder(w);
                for (int i = 0; i < w.length(); ++i) {
                    char orig = sb.charAt(i);
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (c == orig)
                            continue;;
                        sb.setCharAt(i, c);
                        String d = sb.toString();
                        if (d.equals(endWord))
                            return ladderLen;
                        if (dict.contains(d))
                            curr.add(d);
                    }
                    sb.setCharAt(i, orig);
                }
            }
            dict.removeAll(curr);
            prev = curr;
        }
        return 0;
    }
}
