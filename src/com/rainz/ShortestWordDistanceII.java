package com.rainz;

import java.util.*;

/*
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.
 */
public class ShortestWordDistanceII {
    public static void test(String args[]) {
        String[] words1 = {"practice", "makes", "perfect", "coding", "makes"};
        ShortestWordDistanceII obj = new ShortestWordDistanceII(words1);
        System.out.println(obj.shortest("coding", "practice"));
        System.out.println(obj.shortest("makes", "coding"));
    }

    private Map<String, List<Integer>> indicesTable = new HashMap<>();

    public ShortestWordDistanceII(String[] words) {
        for (int i = 0; i < words.length; ++i) {
            String w = words[i];
            List<Integer> indices = indicesTable.get(w);
            if (indices == null) {
                indices = new ArrayList<>();
                indicesTable.put(w, indices);
            }
            indices.add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> indices1 = indicesTable.get(word1);
        List<Integer> indices2 = indicesTable.get(word2);
        int ans = Integer.MAX_VALUE;
        for (int idx: indices1) {
            int pos = Collections.binarySearch(indices2, idx);
            pos = -pos - 1; // now pos is the insertion index;
            if (pos < indices2.size()) {
                int dist = indices2.get(pos) - idx;
                if (dist < ans)
                    ans = dist;
            }
            if (pos > 0) {
                int dist = idx - indices2.get(pos - 1);
                if (dist < ans)
                    ans = dist;
            }
        }
        return ans;
    }
}
