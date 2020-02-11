package com.rainz;

import java.util.*;

public class SentenceSimilarityII {
    public static void test(String args[]) {
        String[][] pairsArr = {{"great", "good"}, {"fine", "good"}, {"acting","drama"}, {"skills","talent"}};
        List<List<String>> pairs = Main.buildList2D(pairsArr);
        String[] w11 = {"great", "acting", "skills"}, w12 = {"fine", "drama", "talent"};
        System.out.println(areSentencesSimilarTwo(w11, w12, pairs));
    }
    private static boolean dfs(String w1, String w2, Map<String, Set<String>> graph, Set<String> visited){
        if (w1.equals(w2))
            return true;
        Set<String> nbs = graph.get(w1);
        if (nbs != null && !visited.contains(w1)){
            visited.add(w1);
            for(String word: nbs){
                if(dfs(word, w2, graph, visited)) return true;
            }
        }

        return false;
    }
    public static boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        int L = words1.length;
        if (words2.length != L)
            return false;
        Map<String, Set<String>> graph = new HashMap<>();

        for(List<String> p : pairs){
            String s1 = p.get(0), s2 = p.get(1);
            Set<String> nbs1 = graph.get(s1);
            if(nbs1 == null){
                nbs1 = new HashSet<>();
                graph.put(s1, nbs1);
            }
            nbs1.add(s2);
            Set<String> nbs2 = graph.get(s2);
            if(nbs2 == null){
                nbs2 = new HashSet<>();
                graph.put(s2, nbs2);
            }
            nbs2.add(s1);
        }

        for(int i = 0 ; i < words1.length; i++){
            Set<String> visited = new HashSet<>();
            if(!dfs(words1[i], words2[i], graph, visited)) return false;
        }

        return true;
    }

    private static String findRootPathCompression(String s, Map<String, String> parentTbl) {
        Stack<String> stk = new Stack<>();
        String curr = s;
        while (curr != null) {
            stk.push(curr);
            curr = parentTbl.get(curr);
        }
        String parent = stk.peek();
        while (!stk.isEmpty()) {
            String n = stk.pop();
            if (n != parent)
                parentTbl.put(n, parent);
        }
        return parent;
    }
    // Maybe we assign each string an ID, and use IDs in union-find
    public static boolean areSentencesSimilarTwoMemLimitExceeded(String[] words1, String[] words2, List<List<String>> pairs) {
        int L = words1.length;
        if (words2.length != L)
            return false;
        // Union-find to get all synonyms into groups
        Map<String, String> parentTbl = new HashMap<>();
        for (List<String> pair: pairs) {
            String s1 = pair.get(0);
            String s2 = pair.get(1);
            String r1 = findRootPathCompression(s1, parentTbl);
            String r2 = findRootPathCompression(s2, parentTbl);
            if (r1 != r2)
                parentTbl.put(r2, r1);
        }

        for (int i = 0; i < L; ++i) {
            String s1 = words1[i];
            String s2 = words2[i];
            if (s1.equals(s2))
                continue;
            String r1 = findRootPathCompression(s1, parentTbl);
            String r2 = findRootPathCompression(s2, parentTbl);
            if (!r1.equals(r2))
                return false;
        }
        return true;
    }
}
