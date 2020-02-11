package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
 * Given a list of query words, return the number of words that are stretchy.
 */
public class ExpressiveWords {
    public static void test(String args[]) {
//        String[] input1 = {"hello", "hi", "helo"};
//        System.out.println(expressiveWords("heeellooo", input1));
        String[] input2 = {"dinnssoo","ddinso","ddiinnso","ddiinnssoo","ddiinso","dinsoo","ddiinsso","dinssoo","dinso"};
        System.out.println(expressiveWords("dddiiiinnssssssoooo", input2));
    }
    private static String encode(String s, List<Integer> counts) {
        StringBuilder sb = new StringBuilder();
        char prev = '\0';
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c != prev) {
                sb.append(c);
                counts.add(1);
            } else {
                int idx = counts.size() - 1;
                counts.set(idx, counts.get(idx) + 1);
            }
            prev = c;
        }
        return sb.toString();
    }
    public static int expressiveWords(String S, String[] words) {
        List<Integer> sCount = new ArrayList<>();
        String sEncode = encode(S, sCount);
        int ans = 0;
        for (String w: words) {
            List<Integer> wCount = new ArrayList<>();
            String wEncode = encode(w, wCount);
            if (!wEncode.equals(sEncode))
                continue;
            boolean match = true;
            for (int i = 0; i < sCount.size() && match; ++i) {
                int sc = sCount.get(i), wc = wCount.get(i);
                if (sc == wc)
                    continue;
                if (sc > wc && sc >= 3)
                    continue;
                match = false;
            }
            if (match)
                ++ans;
        }
        return ans;
    }
}
