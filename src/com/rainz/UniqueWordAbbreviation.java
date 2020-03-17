package com.rainz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * An abbreviation of a word follows the form <first letter><number><last letter>.
 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
 * A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
 */
public class UniqueWordAbbreviation {
    public static void test(String args[]) {
        String[] dict1 = {"deer", "door", "cake", "card"};
        UniqueWordAbbreviation abbr = new UniqueWordAbbreviation(dict1);
        System.out.println(abbr.isUnique("deer"));
        System.out.println(abbr.isUnique("cart"));
        System.out.println(abbr.isUnique("cane"));
        System.out.println(abbr.isUnique("make"));
    }

    private Map<String, Set<String>> abbrFreqs = new HashMap<>();

    private String abbreviate(String w) {
        int L = w.length();
        if (L <= 2)
            return w;
        return String.format("%c%d%c", w.charAt(0), L-2, w.charAt((L-1)));
    }

    public UniqueWordAbbreviation(String[] dictionary) {
        for (String w: dictionary) {
            if (w.length() <= 2)
                continue;
            String abbr = abbreviate(w);
            abbrFreqs.putIfAbsent(abbr, new HashSet<>());
            Set<String> words = abbrFreqs.get(abbr);
            if (words.size() < 2)
                words.add(w);
        }
    }

    public boolean isUnique(String word) {
        String abbr = abbreviate(word);
        Set<String> words = abbrFreqs.get(abbr);
        return (words == null || (words.size() == 1 && words.contains(word)));
    }

}
