package com.rainz;

/*
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).
 * Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.
 */

public class ShortestWaytoFormString {
    public static void test(String args[]) {
        System.out.println(shortestWay("abc", "abcbc"));
        System.out.println(shortestWay("abc", "acdbc"));
        System.out.println(shortestWay("xyz", "xzyxz"));
    }

    public static int shortestWay(String source, String target) {
        int srcIdx = 0, tgtIdx = 0;
        int ans = 0;
        boolean inSequence = false; // is srcIdx in the middle of a subsequence
        while (tgtIdx < target.length()) {
            char t = target.charAt(tgtIdx);
            if (!inSequence) {
                for (srcIdx = 0; srcIdx < source.length(); ++srcIdx) {
                    if (source.charAt(srcIdx) == t) {
                        inSequence = true;
                        ++ans;
                        ++srcIdx;
                        ++tgtIdx;
                        break;
                    }
                }
                if (!inSequence)
                    return -1;
            } else {
                while (srcIdx < source.length() && source.charAt(srcIdx) != t)
                    ++srcIdx;
                if (srcIdx == source.length()) {
                    inSequence = false; // Need to start a new subsequence
                } else {
                    ++srcIdx;
                    ++tgtIdx;
                }
            }
        }
        return ans;
    }
}
