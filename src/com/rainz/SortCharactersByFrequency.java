package com.rainz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Given a string, sort it in decreasing order based on the frequency of characters.
 */
public class SortCharactersByFrequency {
    public static void test(String args[]) {
        System.out.println(frequencySort("tree"));
        System.out.println(frequencySort("cccaaa"));
    }

    public static String frequencySort(String s) {
        int[] freqs = new int[256];
        for (int i = 0; i < s.length(); ++i)
            ++freqs[s.charAt(i)];
        List<int[]> sorted = new ArrayList<>();
        for (int i = 0; i < freqs.length; ++i) {
            if (freqs[i] != 0) {
                int[] rec = {freqs[i], i};
                sorted.add(rec);
            }
        }
        Collections.sort(sorted, (x, y) -> (y[0] - x[0]));
        StringBuilder sb = new StringBuilder();
        for (int[] rec: sorted) {
            char c = (char)rec[1];
            for (int i = 0; i < rec[0]; ++i)
                sb.append(c);
        }
        return sb.toString();
    }
}
