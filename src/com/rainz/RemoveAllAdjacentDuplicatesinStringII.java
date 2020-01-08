package com.rainz;

/*
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made.
 * It is guaranteed that the answer is unique.
 */

public class RemoveAllAdjacentDuplicatesinStringII {
    public static void test(String args[]) {
        System.out.println(removeDuplicates("abcd", 2));
        System.out.println(removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(removeDuplicates("pbbcggttciiippooaais", 2));
    }

    /*
     * Same idea as RemoveAllAdjacentDuplicatesinString, just add a length check for k
     */

    public static String removeDuplicates(String s, int k) {
        if (s.length() < k)
            return s;
        char[] arr = new char[s.length()];
        arr[0] = s.charAt(0);
        int writeIdx = 1;
        for (int readIdx = 1; readIdx < s.length(); ++readIdx) {
            char c = s.charAt(readIdx);
            int runLen = 1;
            int idx = writeIdx;
            while (idx > 0 && c == arr[idx-1]) {
                --idx;
                ++runLen;
            }
            if (runLen < k)
                arr[writeIdx++] = c;
            else
                writeIdx = idx;
        }
        return new String(arr, 0, writeIdx);
    }
}
