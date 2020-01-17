package com.rainz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BraceExpansion {
    public static void test(String args[]) {
        Main.printArray(expand("{a,b}c{d,e}f"));
        Main.printArray(expand("abcd"));
    }

    private static void helper(String S, StringBuilder sb, int start, List<String> ans) {
        if (start >= S.length()) {
            ans.add(sb.toString());
            return;
        }
        char c = S.charAt(start);
        if (c == '{') {
            int idx = S.indexOf('}', start+1);
            for (int opt = start+1; opt < idx; ++opt) {
                char option = S.charAt(opt);
                if (option == ',')
                    continue;
                sb.append(option);
                helper(S, sb, idx+1, ans);
                sb.deleteCharAt(sb.length()-1);
            }
        } else {
            sb.append(c);
            helper(S, sb, start+1, ans);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static String[] expand(String S) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(S, sb, 0, ans);
        Collections.sort(ans);
        String[] arr = new String[ans.size()];
        return ans.toArray(arr);
    }
}
