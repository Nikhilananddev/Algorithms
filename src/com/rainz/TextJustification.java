package com.rainz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu on 2/3/2015.
 */
public class TextJustification {
    public static void test(String args[]) {
        String[] test = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> answer = fullJustify(test, 16);
        for (String s: answer)
            System.out.println("'" + s + "'");
    }

    public static List<String> fullJustify(String[] words, int L) {
        int idx = 0;
        int charsLeft;
        List<String> answer = new ArrayList<String>();
        while (idx < words.length) {
            int startIdx = idx;
            StringBuilder lineBuilder = new StringBuilder(L);
            // Add first word
            lineBuilder.append(words[idx]);
            charsLeft = L - words[idx++].length(); // first word needs no space
            while (idx < words.length && charsLeft > words[idx].length()) {
                charsLeft -= words[idx].length() + 1; // includes a space
                ++idx;
            }
            int numWords = idx - startIdx - 1; // number of words except the first one (already added)
            int spaceTotal = idx != words.length ? charsLeft + numWords : numWords; // if last line, 1 char only
            for (int i = startIdx + 1; i < idx; ++i) {
                int padding = spaceTotal / numWords;
                if (spaceTotal % numWords > 0)
                    ++padding;
                // Add spaces between words
                for (int j = 0; j < padding; ++j)
                    lineBuilder.append(' ');
                lineBuilder.append(words[i]);
                --numWords;
                spaceTotal -= padding;
            }
            // Pad extra space at the end (for the last line, or if a line has only one word)
            while (lineBuilder.length() < L)
                lineBuilder.append(' ');
            answer.add(lineBuilder.toString());
        }
        return answer;
    }
}
