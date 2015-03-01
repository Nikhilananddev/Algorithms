package com.rainz;

/**
 * Created by Yu on 3/1/2015.
 */
public class WordSearch {
    public static void test(String args[]) {
        char[][] test = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'},
        };
        System.out.println(exist(test, "ABCCED"));
        System.out.println(exist(test, "SEE"));
        System.out.println(exist(test, "ABCB"));
    }

    protected static boolean helper(char[][] board, String word, int wordIdx, int row, int col, boolean[][] visited) {
        if (wordIdx == word.length())
            return true;
        char curr = word.charAt(wordIdx);
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir: dirs) {
            int r = row + dir[0];
            if (r < 0 || r >= board.length)
                continue;
            int c = col + dir[1];
            if (c < 0 || c >= board[0].length)
                continue;
            if (curr != board[r][c])
                continue;
            if (visited[r][c])
                continue;
            visited[r][c] = true;
            if (helper(board, word, wordIdx+1, r, c, visited) == true)
                return true;
            visited[r][c] = false;
        }
        return false;
    }

    public static boolean exist(char[][] board, String word) {
        if (word.isEmpty())
            return true;
        if (board.length == 0)
            return false;
        char startChar = word.charAt(0);
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == startChar) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    visited[i][j] = true;
                    if (helper(board, word, 1, i, j, visited))
                        return true;
                }
            }
        }
        return false;
    }

}
