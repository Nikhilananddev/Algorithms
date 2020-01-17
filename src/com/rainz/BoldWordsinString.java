package com.rainz;

/*
 * Given a set of keywords words and a string S, make all appearances of all keywords in S bold. Any letters between <b> and </b> tags become bold.
 * The returned string should use the least number of tags possible, and of course the tags should form a valid combination.
 * For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.
 */
public class BoldWordsinString {
    public static void test(String args[]) {
        String[] words1 = {"ab", "bc"};
        String S1 = "aabcd";
        System.out.println(boldWords(words1, S1));
    }

    public static String boldWords(String[] words, String S) {
        boolean[] bold = new boolean[S.length()];
        for (String w: words) {
            int idx = 0;
            do {
                idx = S.indexOf(w, idx);
                if (idx >= 0) {
                    for (int b = 0; b < w.length(); ++b)
                        bold[idx+b] = true;
                    ++idx;
                }
            } while (idx >= 0 && idx < S.length());
        }
        boolean last = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bold.length; ++i) {
            boolean b = bold[i];
            if (b != last)
                sb.append( b ? "<b>" : "</b>");
            sb.append(S.charAt(i));
            last = b;
        }
        if (bold[bold.length-1])
            sb.append("</b>");
        return sb.toString();
    }
}
