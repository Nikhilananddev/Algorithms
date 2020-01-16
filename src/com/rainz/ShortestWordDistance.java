package com.rainz;

/*
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 */

public class ShortestWordDistance {
    public static void test(String args[]) {
        String[] words1 = {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println(shortestDistance(words1,"coding", "practice"));
        System.out.println(shortestDistance(words1,"makes", "coding"));
    }

    public static int shortestDistance(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; ++i) {
            String w = words[i];
            if (w.compareTo(word1) == 0) {
                idx1 = i;
                if (idx2 >= 0) {
                    int dist = idx1 - idx2;
                    if (dist < ans)
                        ans = dist;
                }
            } else if (w.compareTo(word2) == 0) {
                idx2 = i;
                if (idx1 >= 0) {
                    int dist = idx2 - idx1;
                    if (dist < ans)
                        ans = dist;
                }
            }
        }
        return ans;
    }
}
