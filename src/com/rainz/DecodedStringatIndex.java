package com.rainz;

/*
 * An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:
 * If the character read is a letter, that letter is written onto the tape.
 * If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
 * Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.
 */
public class DecodedStringatIndex {
    public static void test(String args[]) {
        System.out.println(decodeAtIndex("leet2code3", 10));
        System.out.println(decodeAtIndex("ha2", 3));
        System.out.println(decodeAtIndex("ha22", 5));
        System.out.println(decodeAtIndex("a2345678999999999999999", 1));

    }

    /* Idea is: move forward until you have at least K letters, then move backwards */
    public static String decodeAtIndex(String S, int K) {
        if(S == null || S.length() == 0) {
            return S;
        }
        int N = S.length();
        int lastIndex = 0;
        long totalLen = 0;
        for(int i = 0; i < N; i++) {
            char ch = S.charAt(i);
            if( Character.isDigit(ch)) {
                totalLen *= ch - '0';
            } else {
                totalLen++;
            }
            if( totalLen >= K) {
                lastIndex = i;
                break;
            }
        }
        for(; lastIndex >= 0; lastIndex--) {
            char ch = S.charAt(lastIndex);
            if(Character.isDigit(ch)) {
                totalLen /=  (ch - '0');
                if( totalLen != 0)
                    K %= totalLen;

            } else {
                if( K == 0 || K == totalLen) {
                    return ""+ch;
                }
                totalLen--;
            }
        }
        return null;
    }
}
