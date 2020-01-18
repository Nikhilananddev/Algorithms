package com.rainz;

/*
 * Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndexPairsofaString {
    public static void test(String args[]) {
        String text1 = "thestoryofleetcodeandme";
        String[] words1 = {"story","fleet","leetcode"};
        Main.printArray2D(indexPairs(text1, words1));
        String text2 = "ababa";
        String[] words2 = {"aba","ab"};
        Main.printArray2D(indexPairs(text2, words2));
    }

    public static int[][] indexPairs(String text, String[] words) {
        List<int[]> pairs = new ArrayList<>();
        for (String w: words) {
            int idx = 0;
            while (idx >= 0) {
                idx = text.indexOf(w, idx);
                if (idx >= 0) {
                    int[] p = {idx, idx+w.length()-1};
                    pairs.add(p);
                    ++idx;
                }
            }
        }
        Collections.sort(pairs, (x, y) -> x[0] != y[0] ? x[0] - y[0] : x[1] - y[1]);
        int[][] ans = new int[pairs.size()][2];
        for (int i = 0; i < ans.length; ++i) {
            ans[i][0] = pairs.get(i)[0];
            ans[i][1] = pairs.get(i)[1];
        }
        return ans;
    }
}
