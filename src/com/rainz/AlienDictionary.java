package com.rainz;

import java.util.*;

/*
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 */
public class AlienDictionary {
    public static void test(String args[]) {
        String[] input1 = {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        };
        System.out.println(alienOrder(input1));
        String[] input2  = {
                "z",
                "x"
        };
        System.out.println(alienOrder(input2));
        String[] input3  = {
                "z",
                "x",
                "z"
        };
        System.out.println(alienOrder(input3));
        String[] input4  = {
                "z",
                "z"
        };
        System.out.println(alienOrder(input4));
    }

    public static String alienOrder(String[] words) {
        if (words.length == 0)
            return "";
        List<char[]> links = new ArrayList<>();
        Map<Character, Integer> inDegrees = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            String curr = words[i];
            for (int idx = 0; idx < curr.length(); ++idx)
                inDegrees.put(curr.charAt(idx), 0); // initialize in-degree for all letters
            if (i == 0)
                continue;
            String prev = words[i-1];
            for (int idx = 0; idx < prev.length() && idx < curr.length(); ++idx) {
                if (prev.charAt(idx) != curr.charAt(idx)) {
                    char[] l = {prev.charAt(idx), curr.charAt(idx)};
                    links.add(l);
                    break;
                }
            }
        }
        Map<Character, List<Character>> graph = new HashMap<>();
        for (char[] l: links) {
            List<Character> nbs = graph.get(l[0]);
            if (nbs == null) {
                nbs = new ArrayList<>();
                graph.put(l[0], nbs);
            }
            nbs.add(l[1]);
            inDegrees.put(l[0], inDegrees.getOrDefault(l[0], 0));
            inDegrees.put(l[1], inDegrees.getOrDefault(l[1], 0)+1);
        }
        Queue<Character> workQ  = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry: inDegrees.entrySet()) {
            if (entry.getValue() == 0)
                workQ.add(entry.getKey());
        }

        StringBuilder sb = new StringBuilder();
        int visited = 0;
        while (!workQ.isEmpty()) {
            char node = workQ.remove();
            ++visited;
            sb.append(node);
            List<Character> nbs = graph.get(node);
            if (nbs == null)
                continue;
            for (char nb: nbs) {
                int nbDegree = inDegrees.get(nb) - 1;
                inDegrees.put(nb, nbDegree);
                if (nbDegree == 0)
                    workQ.add(nb);
            }
        }
        if (visited != inDegrees.size())
            return "";
        return sb.toString();
    }
}
