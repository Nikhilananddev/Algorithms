package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * You want to form a target string of lowercase letters.
 * At the beginning, your sequence is target.length '?' marks.  You also have a stamp of lowercase letters.
 * On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with the corresponding letter from the stamp.  You can make up to 10 * target.length turns.
 * For example, if the initial sequence is "?????", and your stamp is "abc",  then you may make "abc??", "?abc?", "??abc" in the first turn.  (Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)
 * If the sequence is possible to stamp, then return an array of the index of the left-most letter being stamped at each turn.  If the sequence is not possible to stamp, return an empty array.
 * For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2], corresponding to the moves "?????" -> "abc??" -> "ababc".
 * Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves.  Any answers specifying more than this number of moves will not be accepted.
 */
public class StampingTheSequence {
    public static void test(String args[]) {
        Main.printArray(movesToStamp("abc", "ababc"));
        Main.printArray(movesToStamp("abca", "aabcaca"));
        Main.printArray(movesToStamp("mda", "mdadddaaaa"));
        Main.printArray(movesToStamp("zjmhy", "zmjzjzjmhy"));
    }

    /*
     * Idea is to work backwards: transform target "ababc" back to "?????"
     * First, unstamp "abc" at 2 ==> "ab???"
     * Then, unstamp "ab?" at 0 ==> "?????"
     */
    private static int matchAndUnstamp(String stamp, char[] target, int[] charsLeft) {
        int S = stamp.length();
        final char WILDCARD = '?';
        // Find stamp in target
        for (int tIdx = 0; tIdx + S <= target.length; ++tIdx) {
            int matchCount = 0;
            for (int sIdx = 0; sIdx < S; ++sIdx) {
                char tc = target[tIdx+sIdx];
                if (tc == WILDCARD)
                    continue;
                if (tc == stamp.charAt(sIdx))
                    ++matchCount;
                else {
                    matchCount = 0;
                    break;
                }
            }
            if (matchCount > 0) {
                // Unstamp this
                for (int sIdx = 0; sIdx < S; ++sIdx)
                    target[tIdx+sIdx] = WILDCARD;
                charsLeft[0] -= matchCount;
                return tIdx;
            }
        }
        return -1; // no match
    }

    public static int[] movesToStamp(String stamp, String target) {
        char[] tgt = target.toCharArray();
        int[] charsLeft = { tgt.length };
        List<Integer> steps = new ArrayList<>();
        while(charsLeft[0] > 0) {
            int ind = matchAndUnstamp(stamp, tgt, charsLeft);
            if (ind == -1) {
                steps.clear();
                break;
            }
            steps.add(ind);
        }
        int sz = steps.size();
        int[] ans = new int[sz];
        for (int i = 0; i < sz; ++i)
            ans[i] = steps.get(sz-i-1);
        return ans;
    }

}
