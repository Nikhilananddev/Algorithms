package com.rainz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yu on 1/25/2015.
 */
public class SubstringwithConcatenationofAllWords {
    public static void test(String args[]) {
        String S = "barfoothefoobarmanfoobarxbarfoobarbarxfoo";
        //String S = "foubaabarfo";
        //String S = "barfoobar";
        String[] L = {"foo", "bar"};
        List<Integer> sol = findSubstring(S, L);
        for (Integer x: sol)
            System.out.print("" + x + " ");
        System.out.println();
    }

    public static List<Integer> findSubstring(String S, String[] L) {
        List<Integer> answer = new ArrayList<Integer>();
        int lLength = L.length;
        if (lLength == 0) {
            return answer;
        }
        int wordLen = L[0].length();

        // Build the word table, which contain words in L and their counts
        HashMap<String, Integer> wordTable = new HashMap<String, Integer>();
        for (String l: L) {
            Integer count = wordTable.get(l);
            if (count != null) {
                wordTable.put(l, count.intValue() + 1);
            } else {
                wordTable.put(l, 1);
            }
        }

        // For each starting position
        for (int start = 0; start < wordLen; ++start) {
            // Check if each word is in word map
            String[] strList = new String[(S.length()-start)/wordLen];
            for (int idx = start; idx <= S.length() - wordLen; idx += wordLen) { // note the "<=" here
                int strIdx = (idx - start)/wordLen;
                String w = S.substring(idx, idx + wordLen);
                strList[strIdx] = wordTable.containsKey(w) ? w : null;
            }
            // Now set up the window
            int left = 0, right = 0;
            HashMap<String, Integer> currWindow = new HashMap<String, Integer>();
            for (String l: L)
                currWindow.put(l, 0);
            while (right < strList.length) {
                if (strList[left] == null) {
                    ++left;
                    if (right < left)
                        right = left;
                    continue;
                }
                String strRight = strList[right];
                if (strRight == null) {
                    ++right;
                    left = right;
                    for (String l: L)
                        currWindow.put(l, 0);
                    continue;
                }
                int rightCount = currWindow.get(strRight).intValue() + 1;
                if (rightCount > wordTable.get(strRight).intValue()) {
                    // Too many instances of strRight, contract the window from the left side
                    // Note: if window exceeds max size, we will also be here
                    int leftCount = currWindow.get(strList[left]).intValue();
                    currWindow.put(strList[left], leftCount - 1);
                    ++left; // if further contraction is needed, it'll be done in the next loop(s).
                } else {
                    currWindow.put(strRight, rightCount);
                    if (right - left + 1 == lLength)
                        answer.add(start + left*wordLen);
                    ++right;
                }
            }
        }

        return answer;
    }
}
