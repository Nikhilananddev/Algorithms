package com.rainz;

/*
 * You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible non-empty sequences of letters you can make.
 */

public class LetterTilePossibilities {
    public static void test(String args[]) {
        System.out.println(numTilePossibilities("AAB"));
        System.out.println(numTilePossibilities("AAABBC"));
    }

    /* This is basically finding all permutations for lengths in [1..N]
     */
    private static int helper(char[] buffer, int start, int result) {
        if (start >= buffer.length)
            return result;
        boolean[] seen = new boolean[26];
        char c = buffer[start];
        for (int i = start; i < buffer.length; ++i) {
            char swp = buffer[i];
            if (seen[swp-'A'])
                continue;
            ++result;
            seen[swp-'A'] = true;
            buffer[start] = swp;
            buffer[i] = c;
            result = helper(buffer, start+1, result);
            buffer[start] = c;
            buffer[i] = swp;
        }
        return result;
    }

    public static int numTilePossibilities(String tiles) {
        char[] buffer = tiles.toCharArray();
        return helper(buffer, 0, 0);
    }
}
