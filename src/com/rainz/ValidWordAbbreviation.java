package com.rainz;

/*
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
 * A string such as "word" contains only the following valid abbreviations:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".
 * Note:
 * Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
 */
public class ValidWordAbbreviation {
    public static void test(String args[]) {
        System.out.println(validWordAbbreviation("internationalization", "i12iz4n"));
        System.out.println(validWordAbbreviation("apple", "a2e"));
        System.out.println(validWordAbbreviation("apple", "app2"));
        System.out.println(validWordAbbreviation("apple", "app1"));
    }

    public static boolean validWordAbbreviation(String word, String abbr) {
        int wIdx = 0, aIdx = 0, num = 0;
        while (aIdx < abbr.length()) {
            char c = abbr.charAt(aIdx++);
            if (Character.isDigit(c)) {
                if (num == 0 && c == '0')
                    return false; // leading 0s are not allowed
                num = num * 10 + c - '0';
                continue;
            }
            wIdx += num;
            if (wIdx >= word.length())
                return false;
            num = 0;
            if (c != word.charAt(wIdx++))
                return false;
        }

        return wIdx + num == word.length();
    }
}
