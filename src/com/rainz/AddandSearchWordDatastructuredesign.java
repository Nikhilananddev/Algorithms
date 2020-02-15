package com.rainz;

/*
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 */
public class AddandSearchWordDatastructuredesign {
    public static void test(String args[]) {
        AddandSearchWordDatastructuredesign dict = new AddandSearchWordDatastructuredesign();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println(dict.search("pad")); // -> false
        System.out.println(dict.search("bad")); // -> true
        System.out.println(dict.search(".ad")); // -> true
        System.out.println(dict.search("b..")); // -> true
    }

    class TrieNode {
        public boolean endOfWord = false;
        public TrieNode[] links = new TrieNode[26];
    }
    private TrieNode _root = new TrieNode();

    private boolean searchHelper(String s, int start, TrieNode root) {
        TrieNode curr = root;
        boolean endOfWord = curr.endOfWord;
        for (int i = start; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c != '.') {
                TrieNode n = curr.links[c - 'a'];
                if (n == null)
                    return false;
                if (i == s.length() - 1)
                    endOfWord = n.endOfWord;
                curr = n;
            } else {
                for (TrieNode child: curr.links) {
                    if (child == null)
                        continue;
                    if (searchHelper(s, i+1, child))
                        return true;
                }
                return false;
            }
        }
        return endOfWord;
    }

    public AddandSearchWordDatastructuredesign() {

    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr = _root;
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

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchHelper(word, 0, _root);
    }
}
