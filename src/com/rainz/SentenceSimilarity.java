package com.rainz;

import java.util.*;

/*
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.
 * For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].
 * Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.
 * However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.
 * Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"]
 */
public class SentenceSimilarity {
    public static void test(String args[]) {
        String[][] pairsArr = {{"great", "fine"}, {"acting","drama"}, {"skills","talent"}};
        List<List<String>> pairs = Main.buildList2D(pairsArr);
        String[] w11 = {"great",  "acting",  "skills"}, w12 = {"fine", "drama", "talent"};
        System.out.println(areSentencesSimilar(w11, w12, pairs));
    }

    public static boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        int L = words1.length;
        if (words2.length != L)
            return false;
        Map<String, Set<String>> similar = new HashMap<>();
        for (List<String> p: pairs) {
            String s1 = p.get(0), s2 = p.get(1);
            Set<String> sim = similar.get(s1);
            if (sim == null) {
                sim = new HashSet<>();
                similar.put(s1, sim);
            }
            sim.add(s2);
            sim = similar.get(s2);
            if (sim == null) {
                sim = new HashSet<>();
                similar.put(s2, sim);
            }
            sim.add(s1);
        }
        for (int i = 0; i < L; ++i) {
            String s1 = words1[i];
            String s2 = words2[i];
            if (s1.equals(s2))
                continue;
            Set<String> sim = similar.get(s1);
            if (sim == null)
                return false;
            if (!sim.contains(s2))
                return false;
        }
        return true;
    }

}
