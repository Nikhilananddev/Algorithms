package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.
 * For each such occurrence, add "third" to the answer, and return the answer.
 */
public class OccurrencesAfterBigram {
    public static void test(String args[]) {
        Main.printArray(findOcurrences("alice is a good girl she is a good student", "a", "good"));
        Main.printArray(findOcurrences("we will we will rock you", "we", "will"));
    }

    public static String[] findOcurrences(String text, String first, String second) {
        List<String> ans = new ArrayList<>();
        String[] words = text.split(" ");
        for (int i = 2; i < words.length; ++i) {
            if (words[i-2].equals(first) && words[i-1].equals(second))
                ans.add(words[i]);
        }
        String[] arr = new String[ans.size()];
        for (int i = 0; i < arr.length; ++i)
            arr[i] = ans.get(i);
        return arr;
    }

}
