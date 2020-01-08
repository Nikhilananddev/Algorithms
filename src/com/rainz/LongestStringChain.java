package com.rainz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Given a list of words, each word consists of English lowercase letters.
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 */
public class LongestStringChain {
    public static void test(String args[]) {
        String[] input1 = {"a","b","ba","bca","bda","bdca"};
        System.out.println(longestStrChain(input1));
    }

    public static int longestStrChain(String[] words) {
        if (words.length == 0)
            return 0;
        int ans = 1;
        Map<String, Integer> chainTable = new HashMap<>();
        Map<Integer, Set<String>> lengthTable = new HashMap<>();
        for (String w: words) {
            int len = w.length();
            Set<String> wordSet = lengthTable.get(len);
            if (wordSet == null) {
                wordSet = new HashSet<>();
                lengthTable.put(len, wordSet);
            }
            wordSet.add(w);
            chainTable.put(w, 1);
        }
        Map.Entry<Integer, Set<String>> prevEntry = null;
        for (Map.Entry<Integer, Set<String>> entry: lengthTable.entrySet()) {
            int currLen = entry.getKey();
            if (prevEntry != null && prevEntry.getKey() == currLen - 1) {
                Set<String> prevWords = prevEntry.getValue();
                for (String w: entry.getValue()) {
                    int maxCurrChain = chainTable.get(w);
                    // Remove each char in curr word and find it in prev wordset
                    for (int i = 0; i < w.length(); ++i) {
                        StringBuilder sb = new StringBuilder(w);
                        sb.deleteCharAt(i);
                        String removed = sb.toString();
                        if (prevWords.contains(removed)) {
                            int len = chainTable.get(removed) + 1;
                            if (len > maxCurrChain)
                                maxCurrChain = len;
                        }
                    }
                    if (ans < maxCurrChain)
                        ans = maxCurrChain;
                    chainTable.put(w, maxCurrChain);
                }
            }
            prevEntry = entry;
        }

        return ans;
    }
}
