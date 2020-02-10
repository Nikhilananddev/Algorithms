package com.rainz;

import java.util.*;

/*
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 */
public class WordBreakII {
    public static void test(String args[]) {
        List<String> input1 = List.of("cat", "cats", "and", "sand", "dog");
        System.out.println(wordBreak("catsanddog", input1));
        List<String> input2 = List.of("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(wordBreak("pineapplepenapple", input2));
        List<String> input3 = List.of("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak("catsandog", input3));
        List<String> input4 = List.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        System.out.println(wordBreak(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                input4));
    }

    private static List<String> helper(String s, int start, List<String> wordDict, Map<Integer, List<String>> memo) {
        List<String> cached = memo.get(start);
        if (cached != null)
            return cached;
        List<String> res = new ArrayList<>();
        if (start >= s.length()) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            int end = start + word.length();
            if (end > s.length())
                continue;
            String w = s.substring(start, end);
            if (!w.equals(word))
                continue;
            List<String> remain = helper(s, start + word.length(), wordDict, memo);
            for (String str: remain) {
                StringBuilder sb = new StringBuilder(word);
                if (str.length() > 0) {
                    sb.append(' ');
                    sb.append(str);
                }
                res.add(sb.toString());
            }
        }
        memo.put(start, res);
        return res;
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer, List<String>> memo = new HashMap<>();
        return helper(s, 0, wordDict, memo);

    }
    public static List<String> wordBreakDPTimeOut(String s, List<String> wordDict) {
        int L = s.length();
        Set<String> wDict = new HashSet<>(wordDict);
        List<String>[] dp = new List[L+1]; // dp[i] stores word breaks *before* i
        dp[0] = new ArrayList<>();
        dp[0].add(""); // initial string to start with
        for (int i = 1; i <= L; ++i) {
            dp[i] = new ArrayList<>();
            for (int j = 0; j < i; ++j) {
                // No previous word break at j, or [j, i) is not a word
                String w = s.substring(j, i);
                if (dp[j].isEmpty() || !wDict.contains(w))
                    continue;
                for (String brk: dp[j]) {
                    StringBuilder sb = new StringBuilder(brk);
                    if (sb.length() > 0)
                        sb.append(' ');
                    sb.append(w);
                    dp[i].add(sb.toString());
                }
            }
        }
        return dp[L];
    }
}
