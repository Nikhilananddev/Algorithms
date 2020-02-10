package com.rainz;

/*
 * Write a function that takes a string as input and reverse only the vowels of a string.
 */

import java.util.HashSet;
import java.util.Set;

public class ReverseVowelsofaString {
    public static void test(String args[]) {
        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels("leetcode"));
    }
    public static String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        int L = s.length();
        int start = 0, end = L-1;
        char[] output = s.toCharArray();
        while (start < end) {
            char sc = output[start];
            if (!vowels.contains(Character.toLowerCase(sc))) {
                ++start;
                continue;
            }
            char ec = output[end];
            if (!vowels.contains(Character.toLowerCase(ec))) {
                --end;
                continue;
            }
            output[start] = ec;
            output[end] = sc;
            ++start;
            --end;
        }
        return new String(output);
    }

}
