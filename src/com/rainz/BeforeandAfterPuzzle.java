package com.rainz;

import java.util.*;

/*
 * Given a list of phrases, generate a list of Before and After puzzles.
 * A phrase is a string that consists of lowercase English letters and spaces only. No space appears in the start or the end of a phrase. There are no consecutive spaces in a phrase.
 * Before and After puzzles are phrases that are formed by merging two phrases where the last word of the first phrase is the same as the first word of the second phrase.
 * Return the Before and After puzzles that can be formed by every two phrases phrases[i] and phrases[j] where i != j. Note that the order of matching two phrases matters, we want to consider both orders.
 * You should return a list of distinct strings sorted lexicographically.
 */
public class BeforeandAfterPuzzle {
    public static void test(String args[]) {
        String[] input1 = {"writing code","code rocks"};
        System.out.println(beforeAndAfterPuzzles(input1));
        String[] input2 = {"mission statement",
                            "a quick bite to eat",
                            "a chip off the old block",
                            "chocolate bar",
                            "mission impossible",
                            "a man on a mission",
                            "block party",
                            "eat my word",
                            "bar of soap"};
        System.out.println(beforeAndAfterPuzzles(input2));
        String[] input3 = {"a","b","a"};
        System.out.println(beforeAndAfterPuzzles(input3));
    }

    public static List<String> beforeAndAfterPuzzles(String[] phrases) {
        Set<String> ansSet = new TreeSet<>();
        String[][] phraseWords = new String[phrases.length][];
        Map<String, List<Integer>> startTable = new HashMap<>();
        Map<String, List<Integer>> endTable = new HashMap<>();
        for (int i = 0; i < phrases.length; ++i) {
            String p = phrases[i];
            phraseWords[i] = p.split(" ");
            List<Integer> startList = startTable.get(phraseWords[i][0]);
            if (startList == null) {
                startList = new ArrayList<>();
                startTable.put(phraseWords[i][0], startList);
            }
            startList.add(i);
            int phraseLen = phraseWords[i].length;
            List<Integer> endList = endTable.get(phraseWords[i][phraseLen-1]);
            if (endList == null) {
                endList = new ArrayList<>();
                endTable.put(phraseWords[i][phraseLen-1], endList);
            }
            endList.add(i);
        }

        for (Map.Entry<String, List<Integer>> endEntry: endTable.entrySet()) {
            List<Integer> startList = startTable.get(endEntry.getKey());
            if (startList == null)
                continue;
            for (int e: endEntry.getValue()) {
                for (int s: startList) {
                    if (e == s)
                        continue;
                    List<String> result = new ArrayList<>();
                    for (int idx = 0; idx < phraseWords[e].length-1; ++idx)
                        result.add(phraseWords[e][idx]);
                    for (int idx = 0; idx < phraseWords[s].length; ++idx)
                        result.add(phraseWords[s][idx]);
                    ansSet.add(String.join(" ", result));
                }
            }
        }
        List<String> ans = new ArrayList<>();
        ans.addAll(ansSet);
        return ans;
    }
}
