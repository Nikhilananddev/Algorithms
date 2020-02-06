package com.rainz;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public static void test(String args[]) {
        List<String> dict1 = List.of("leet", "code");
        System.out.println(wordBreak("leetcode", dict1));
        List<String> dict2 = List.of("apple", "pen");
        System.out.println(wordBreak("applepenapple", dict2));
        List<String> dict3 = List.of("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak("catsandog", dict3));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        int L = s.length();
        boolean[] dp = new boolean[L + 1];
        Set<String> dictSet = new HashSet<>(wordDict);
        dp[0] = true;
        for (int end = 1; end <= L; ++end) {
            for (int start = 0; start < end; ++start) {
                String w = s.substring(start, end);
                if (!dictSet.contains(w))
                    continue;
                if (dp[start]) {
                    dp[end] = true;
                    break;
                }
            }
        }
        return dp[L];
    }

    public static boolean wordBreakOldPassed(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        Set<String> dictSet = new HashSet<>();
        dictSet.addAll(wordDict);
        for (int i = 0; i < s.length(); ++i) {
            for (int j = i; j >= 0; --j) {
                String w = s.substring(j, i+1);
                if (!dictSet.contains(w))
                    continue;
                if (j == 0 || dp[j-1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()-1];
    }
}
