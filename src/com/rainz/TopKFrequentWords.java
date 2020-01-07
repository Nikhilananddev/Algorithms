package com.rainz;

import java.util.*;

public class TopKFrequentWords {
    public static void test(String args[]) {
        String[] input1 = {"i", "love", "leetcode", "i", "love", "coding"};
        Main.printList(topKFrequent(input1, 2));
        String[] input2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        Main.printList(topKFrequent(input2, 4));
    }

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freqs = new HashMap<>();
        for (String s: words)
            freqs.put(s, freqs.getOrDefault(s, 0)+1);
        Queue<Map.Entry<String, Integer>> pq = new PriorityQueue<>( (x, y) ->
                x.getValue() != y.getValue() ? y.getValue() - x.getValue() : x.getKey().compareTo(y.getKey()) );
        for (Map.Entry<String, Integer> entry: freqs.entrySet())
            pq.add(entry);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < k; ++i)
            ans.add(pq.remove().getKey());
        return ans;
    }
}
