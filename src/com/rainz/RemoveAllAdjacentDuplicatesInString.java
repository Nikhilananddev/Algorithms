package com.rainz;

/*
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
 * We repeatedly make duplicate removals on S until we no longer can.
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 */
public class RemoveAllAdjacentDuplicatesInString {
    public static void test(String args[]) {
        System.out.println(removeDuplicates("abbaca"));
    }

    /*
     * When we see a different char, write it. When we see a dup char, use it to "erase" previous dup chars.
     * Note that we don't need multiple passes.
     * Zuma-like games might need multiple passes because we are given a string in "stable" state then modify it.
     */
    public static String removeDuplicates(String S) {
        if (S.length() < 2)
            return S;
        char[] s = new char[S.length()];
        s[0] = S.charAt(0);
        int writeIdx = 1;
        for (int readIdx = 1; readIdx < S.length(); ++readIdx) {
            char c = S.charAt(readIdx);
            boolean isDup = false;
            while (writeIdx > 0 && c == s[writeIdx-1]) {
                --writeIdx;
                isDup = true;
            }
            if (!isDup)
                s[writeIdx++] = c;
        }
        return new String(s, 0, writeIdx);
    }
}
