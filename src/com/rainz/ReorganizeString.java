package com.rainz;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * If possible, output any possible result.  If not possible, return the empty string.
 */

public class ReorganizeString {
    public static void test(String args[]) {
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));
    }

    public static String reorganizeString(String S) {
        Map<Character, Integer> freqs = new HashMap<>();
        for (int i = 0; i < S.length(); ++i) {
            char c = S.charAt(i);
            Integer count = freqs.get(c);
            if (count == null)
                count = 0;
            freqs.put(c, count+1);
        }

        char[] output = new char[S.length()];
        int idx = 0;
        PriorityQueue<Map.Entry<Character, Integer>> pq =
                new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
        for (Map.Entry<Character, Integer> entry: freqs.entrySet()) {
            pq.add(entry);
        }
        char lastChar = 0;
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.remove();
            char c = entry.getKey();
            if (lastChar == c) {
                // Can't use the same entry, get the next one
                if (pq.isEmpty())
                    return "";
                Map.Entry<Character, Integer> entry2 = pq.remove();
                pq.add(entry); // put first entry back
                // Decrement 2nd entry and put it back
                c = entry2.getKey();
                int newVal = entry2.getValue() - 1;
                if (newVal > 0) {
                    entry2.setValue(newVal);
                    pq.add(entry2);
                }
            } else {
                // Decrement entry and put it back
                int newVal = entry.getValue() - 1;
                if (newVal > 0) {
                    entry.setValue(newVal);
                    pq.add(entry);
                }
            }
            output[idx++] = c;
            lastChar = c;
        }
        return new String(output);
    }
}
