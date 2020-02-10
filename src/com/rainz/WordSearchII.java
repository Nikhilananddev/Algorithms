package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 */
public class WordSearchII {
    public static void test(String args[]) {
        char[][] board1 = {
                            {'o', 'a', 'a', 'n'},
                            {'e', 't', 'a', 'e'},
                            {'i', 'h', 'k', 'r'},
                            {'i', 'f', 'l', 'v'}
                         };
        String[] words1 = {"oath","pea","eat","rain"};
        System.out.println(findWords(board1, words1));
    }

    private static class TrieNode {
        public boolean endOfWord = false;
        public TrieNode[] links = new TrieNode[26];

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode curr = this;
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
    }

    private static void helper(char[][] board, int row, int col, TrieNode node, boolean[][] visited, List<Character> word, List<String> ans) {
        int R = board.length, C = board[0].length;
        if (node.endOfWord) {
            StringBuilder sb = new StringBuilder();
            for (char ch: word)
                sb.append(ch);
            ans.add(sb.toString());
            node.endOfWord = false; // don't search for this any more
        }
        visited[row][col] = true;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir: dirs) {
            int r = row + dir[0];
            int c = col + dir[1];
            if (r < 0 || r >= R || c < 0 || c >= C || visited[r][c])
                continue;
            char ch = board[r][c];
            TrieNode nextNode = node.links[ch-'a'];
            if (nextNode == null)
                continue;
            word.add(ch);
            helper(board, r, c, nextNode, visited, word, ans);
            word.remove(word.size()-1);
        }
        visited[row][col] = false;
    }

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        if (words.length == 0)
            return ans;
        int R = board.length;
        if (R == 0)
            return ans;
        int C = board[0].length;
        boolean[][] visited = new boolean[R][C];
        TrieNode root = new TrieNode();
        for (String w: words)
            root.insert(w);
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                char ch = board[r][c];
                TrieNode node = root.links[ch-'a'];
                if (node != null) {
                    List<Character> word = new ArrayList<>();
                    word.add(ch);
                    helper(board, r, c, node, visited, word, ans);
                    word.remove(word.size()-1);
                }
            }
        }
        return ans;
    }
}
