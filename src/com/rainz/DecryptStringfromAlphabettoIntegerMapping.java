package com.rainz;

/*
 * Given a string s formed by digits ('0' - '9') and '#' . We want to map s to English lowercase characters as follows:
 * Characters ('a' to 'i') are represented by ('1' to '9') respectively.
 * Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
 * Return the string formed after mapping.
 * It's guaranteed that a unique mapping will always exist.
 */

public class DecryptStringfromAlphabettoIntegerMapping {
    public static void test(String args[]) {
        System.out.println(freqAlphabets("10#11#12"));
        System.out.println(freqAlphabets("1326#"));
        System.out.println(freqAlphabets("25#"));
        System.out.println(freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"));
    }

    public static String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        int L = s.length();
        int idx = 0;
        while (idx < s.length()) {
            if (idx+2 >= s.length() || s.charAt(idx+2) != '#') {
                sb.append((char)(s.charAt(idx) - '1' + 'a'));
                ++idx;
                continue;
            }
            int code = s.charAt(idx) - '0';
            code = code * 10 + s.charAt(idx+1) - '0';
            sb.append((char)('a' + code - 1));
            idx += 3;
        }
        return sb.toString();
    }
}
