package com.rainz;

import java.util.HashSet;
import java.util.Set;

/*
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
 * The rules of Goat Latin are as follows:
 * If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 * For example, the word 'apple' becomes 'applema'.
 * If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 * Return the final sentence representing the conversion from S to Goat Latin.
 */
public class GoatLatin {
    public static void test(String args[]) {
        System.out.println(toGoatLatin("I speak Goat Latin"));
        System.out.println(toGoatLatin("The quick brown fox jumped over the lazy dog"));
    }
    public static String toGoatLatin(String S) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a'); vowels.add('e'); vowels.add('i'); vowels.add('o'); vowels.add('u');
        String[] parts = S.split(" ");
        String[] processed = new String[parts.length];
        for (int i = 0; i < parts.length; ++i) {
            String p = parts[i];
            StringBuilder sb = new StringBuilder(p);
            char c1 = p.charAt(0);
            if (!vowels.contains(Character.toLowerCase(c1))) {
                sb.deleteCharAt(0);
                sb.append(c1);
            }
            sb.append("ma");
            for (int idx = 0; idx < i+1; ++idx)
                sb.append('a');
            processed[i] = sb.toString();
        }
        return String.join(" ", processed);
    }
}
