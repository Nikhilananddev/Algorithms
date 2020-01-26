package com.rainz;

import java.util.*;

/*
 * Given a list of pairs of equivalent words synonyms and a sentence text, Return all possible synonymous sentences sorted lexicographically.
 * Constraints:
 * 0 <= synonyms.length <= 10
 * synonyms[i].length == 2
 * synonyms[0] != synonyms[1]
 * All words consist of at most 10 English letters only.
 * text is a single space separated sentence of at most 10 words.
 */
public class SynonymousSentences {
    public static void test(String args[]) {
//        String[][] input1 = {{"happy","joy"},{"sad","sorrow"},{"joy","cheerful"}};
//        System.out.println(generateSentences(Main.buildList2D(input1), "I am happy today but was sad yesterday"));
        String[][] input2 = {{"a","QrbCl"}};
        System.out.println(generateSentences(Main.buildList2D(input2), "d QrbCl ya ya NjZQ"));
    }

    private static void dfs(Map<String, Set<String>> synTbl, String[] parts, int start, List<String> words, List<String> ans) {
        if (start >= parts.length) {
            String sentence = String.join(" ", words);
            ans.add(sentence);
            return;
        }
        String s = parts[start];
        Set<String> options = synTbl.get(s);
        if (options == null) {
            options = new HashSet<>();
            options.add(s);
        }
        for (String opt: options) {
            words.add(opt);
            dfs(synTbl, parts, start+1, words, ans);
            words.remove(words.size()-1);
        }
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

    public static List<String> generateSentences(List<List<String>> synonyms, String text) {
        // Union-find to get all synonyms into groups
        Map<String, String> parentTbl = new HashMap<>();
        for (List<String> pair: synonyms) {
            String s1 = pair.get(0);
            String s2 = pair.get(1);
            String r1 = findRootPathCompression(s1, parentTbl);
            String r2 = findRootPathCompression(s2, parentTbl);
            if (r1 != r2)
                parentTbl.put(r2, r1);
        }
        // Build synonyms table
        Map<String, Set<String>> synTbl = new HashMap<>();
        for (Map.Entry<String, String> e: parentTbl.entrySet()) {
            String s = e.getKey();
            String r = findRootPathCompression(s, parentTbl);
            Set<String> syns = synTbl.get(r);
            if (syns == null) {
                syns = new HashSet<>();
                synTbl.put(r, syns);
                syns.add(r);
            }
            syns.add(s);
            synTbl.put(s, syns);
        }
        String[] parts = text.split(" ");
        List<String> ans = new ArrayList<>();
        List<String> words = new ArrayList<>();
        dfs(synTbl, parts, 0, words, ans);
        Collections.sort(ans);
        return ans;
    }

}
