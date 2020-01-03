package com.rainz;

import javax.print.attribute.standard.OrientationRequested;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ImplementMagicDictionary {
    public static void test(String args[]) {
        String[] input = {"hello", "hallo", "leetcode"};
        ImplementMagicDictionary d = new ImplementMagicDictionary();
        d.buildDict(input);
        System.out.println((d.search("hello")));
        System.out.println((d.search("hallo")));
        System.out.println((d.search("hhllo")));
        System.out.println((d.search("hell")));
        System.out.println((d.search("leetcoded")));
    }

    private Set<String> magicDict = new HashSet<>();

    /** Initialize your data structure here. */
    public ImplementMagicDictionary() {

    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        magicDict.clear();
        for (String word: dict)
            magicDict.add(word);
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        for (String d: magicDict) {
            if (d.length() != word.length())
                continue;
            int diff = 0;
            for (int i = 0; i < word.length() && diff < 2; ++i) {
                if (d.charAt(i) != word.charAt(i))
                    ++diff;
            }
            if (diff == 1)
                return true;
        }
        return false;
    }

}
