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
        // Note: we could use an int array of 26/256 instead of a HashMap.
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

        /*
         * Note: a better way to do this is to actually take 2 entries at a time out of PQ
         * These 2 entries (A & B) are guaranteed to have different numbers. Decrement both and put them back.
         * Even if next time they are still the top two, they will show up as A & B, because we decremented both by 1.
         * But, in order to do this, you must guarantee no single letter appeared more than (n+1)/2 times.
         */
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
