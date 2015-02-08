package com.rainz;

/**
 * Created by Yu on 2/8/2015.
 */
public class LengthofLastWord {
    public static void test(String args[]) {
        System.out.println(lengthOfLastWord("Hello World")); // 5
        System.out.println(lengthOfLastWord("abc ")); // 3
    }

    public static int lengthOfLastWord(String s) {
        int answer = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) != ' ')
                ++answer;
            else if (answer > 0) // handle cases like "abc "
                break; // we've seen a word and this is the space after the word
        }
        return answer;
    }
}
