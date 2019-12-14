package com.rainz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchSuggestionsSystem {
    public static void test(String args[]) {
        String[] products1 = {"mobile","mouse","moneypot","monitor","mousepad"};
        String[] products2 = {"havana"};
        String[] products3 = {"bags","baggage","banner","box","cloths"};
        String[] products4 = {"havana"};

        var result = suggestedProducts(products1, "mouse");
        result.forEach( value -> {value.forEach( v -> System.out.print( ""+v+",")); System.out.println(); });
        System.out.println("=========================");

        result = suggestedProducts(products2, "havana");
        result.forEach( value -> {value.forEach( v -> System.out.print( ""+v+",")); System.out.println(); });
        System.out.println("=========================");

        result = suggestedProducts(products3, "bags");
        result.forEach( value -> {value.forEach( v -> System.out.print( ""+v+",")); System.out.println(); });
        System.out.println("=========================");

        result = suggestedProducts(products4, "tatiana");
        result.forEach( value -> {value.forEach( v -> System.out.print( ""+v+",")); System.out.println(); });
        System.out.println("=========================");
    }

    static class TrieNode {
        public Map<Character, TrieNode> children = new TreeMap<>();
        public boolean isEnd = false; // don't forget this!!!
    }

    private static void findFirstN(TrieNode node, List<Character> prefix, int n, List<String> matches) {
        if (matches.size() >= n)
            return;
        if (node.children.isEmpty() || node.isEnd) {
            StringBuilder bld = new StringBuilder();
            for (Character c: prefix)
                bld.append(c);
            matches.add(bld.toString());
        }
        for (Map.Entry<Character, TrieNode> entry: node.children.entrySet()) {
            prefix.add(entry.getKey());
            findFirstN(entry.getValue(), prefix, n, matches);
            prefix.remove(prefix.size()-1);
        }
    }

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        // Build Trie
        TrieNode root = new TrieNode();
        for (String p: products) {
            TrieNode curr = root;
            for (int i = 0; i < p.length(); ++i) {
                char c = p.charAt(i);
                TrieNode child = curr.children.get(c);
                if (child == null) {
                    child = new TrieNode();
                    curr.children.put(c, child);
                }
                curr = child;
            }
            curr.isEnd = true;
        }
        // Search Trie
        TrieNode curr = root;
        List<Character> prefix = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); ++i) {
            List<String> matches = new ArrayList<>();
            result.add(matches);
            char c = searchWord.charAt(i);
            prefix.add(c);
            curr = curr != null ? curr.children.get(c) : null;
            if (curr == null)
                continue;
            findFirstN(curr, prefix, 3, matches);
        }

        return result;
    }
}
