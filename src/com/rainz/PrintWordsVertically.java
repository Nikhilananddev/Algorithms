package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string s. Return all the words vertically in the same order in which they appear in s.
 * Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
 * Each word would be put on only one column and that in one column there will be only one word.
 */
public class PrintWordsVertically {
    public static void test(String args[]) {
        System.out.println(printVertically("HOW ARE YOU"));
        System.out.println(printVertically("TO BE OR NOT TO BE"));
        System.out.println(printVertically("CONTEST IS COMING"));
    }
    public static List<String> printVertically(String s) {
        String[] parts = s.split(" ");
        int N = parts.length;
        int maxLen = 0;
        int[] maxFromRight = new int[N];
        for (int i = N-1; i >= 0; --i) {
            int l = parts[i].length();
            if (l > maxLen)
                maxLen = l;
            maxFromRight[i] = maxLen;
        }
        List<String> ans = new ArrayList<>();
        for (int r = 0; r < maxLen; ++r) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < N; ++c) {
                if (r >= maxFromRight[c])
                    break;
                String p = parts[c];
                if (p.length() == 0)
                    continue;
                sb.append(r < p.length() ? p.charAt(r) : ' ');
            }
            ans.add(sb.toString());
        }
        return ans;
    }
}
