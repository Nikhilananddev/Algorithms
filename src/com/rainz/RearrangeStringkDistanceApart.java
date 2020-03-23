package com.rainz;

/*
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 */

import java.util.*;

public class RearrangeStringkDistanceApart {
    public static void test(String args[]) {
        System.out.println(rearrangeString("aabbcc", 3));
        System.out.println(rearrangeString("aaabc", 3));
        System.out.println(rearrangeString("aaadbbcc", 2));
    }

    public static String rearrangeString(String s, int k) {
        // Note the special case for k == 0
        if (k == 0)
            return s;

        int[] freqs = new int[26];
        int L = s.length();
        for (int i = 0; i < L; ++i)
            ++freqs[s.charAt(i)-'a'];
        /*
         * It is important here to compare not just count but also the letter itself.
         * This is to guarantee that, between 2 loops, same letter is k distance apart
         * For example abcabc (good) vs abcacb (bad) for k=3
         */
        Queue<int[]> pq = new PriorityQueue<>((x, y) -> y[0] != x[0] ? y[0]-x[0] : x[1]-y[1]);
        for (int i = 0; i < freqs.length; ++i) {
            if (freqs[i] > 0) {
                int[] rec = {freqs[i], i}; // counter, letter-'a'
                pq.offer(rec);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            // Grab (up to) k letters
            int n = Math.min(L - sb.length(), k);
            List<int[]> putBack = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (pq.isEmpty())
                    return ""; // out of letter to fill next position
                int[] rec = pq.poll();
                sb.append((char)(rec[1]+'a'));
                --rec[0];
                if (rec[0] > 0)
                    putBack.add(rec);
            }
            for (int[] rec: putBack)
                pq.offer(rec);
        }
        return sb.toString();
    }
}
