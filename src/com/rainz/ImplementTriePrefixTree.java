package com.rainz;

public class ImplementTriePrefixTree {
    public static void test(String args[]) {
        ImplementTriePrefixTree trie = new ImplementTriePrefixTree();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }

    class TrieNode {
        public boolean endOfWord = false;
        public TrieNode[] links = new TrieNode[26];
    }
    private TrieNode root = new TrieNode();

    private boolean searchHelper(String s, boolean isWord) {
        TrieNode curr = root;
        boolean endOfWord = false;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            TrieNode n = curr.links[c-'a'];
            if (n == null)
                return false;
            if (i == s.length()-1)
                endOfWord = n.endOfWord;
            curr = n;
        }
        return (!isWord || endOfWord);
    }

    /** Initialize your data structure here. */
    public ImplementTriePrefixTree() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            TrieNode n = curr.links[c-'a'];
            if (n == null) {
                n = new TrieNode();
                curr.links[c - 'a'] = n;
            }
            if (i == word.length()-1)
                n.endOfWord = true;
            curr = n;
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return searchHelper(word, true);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchHelper(prefix, false);
    }
}
