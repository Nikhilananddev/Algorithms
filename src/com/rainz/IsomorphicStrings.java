package com.rainz;

/*
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 */
public class IsomorphicStrings {
    public static void test(String args[]) {
        System.out.println(isIsomorphic("egg", "add"));
        System.out.println(isIsomorphic("foo", "bar"));
        System.out.println(isIsomorphic("paper", "title"));
        System.out.println(isIsomorphic("ab", "aa"));
    }
    /*
     * Create and check s-to-t mappings and t-to-s mappings
     * Important: must check BOTH mappings!!!
     */
    public static boolean isIsomorphic(String s, String t) {
        int L = s.length();
        if (t.length() != L)
            return false;
        char [] stMapping = new char[256];
        char [] tsMapping = new char[256];
        for (int i = 0; i < L; ++i) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            // Check s-to-t mapping
            char stm = stMapping[sc];
            if (stm == '\0') {
                stMapping[sc] = tc;
            } else if (stm != tc)
                return false;
            // Check t-to-s mapping
            char tsm = tsMapping[tc];
            if (tsm == '\0') {
                tsMapping[tc] = sc;
            } else if (tsm != sc)
                return false;
        }
        return true;
    }
}
