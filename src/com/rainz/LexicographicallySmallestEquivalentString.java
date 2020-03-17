package com.rainz;

import java.util.Stack;

/*
 * Given strings A and B of the same length, we say A[i] and B[i] are equivalent characters. For example, if A = "abc" and B = "cde", then we have 'a' == 'c', 'b' == 'd', 'c' == 'e'.
 * Equivalent characters follow the usual rules of any equivalence relation:
 * Reflexivity: 'a' == 'a'
 * Symmetry: 'a' == 'b' implies 'b' == 'a'
 * Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'
 * For example, given the equivalency information from A and B above, S = "eed", "acd", and "aab" are equivalent strings, and "aab" is the lexicographically smallest equivalent string of S.
 * Return the lexicographically smallest equivalent string of S by using the equivalency information from A and B.
 */

public class LexicographicallySmallestEquivalentString {
    public static void test(String args[]) {
        System.out.println(smallestEquivalentString("parker", "morris", "parser"));
        System.out.println(smallestEquivalentString("hello", "world", "hold"));
        System.out.println(smallestEquivalentString("leetcode", "programs", "sourcecode"));
    }

    private static int findRootPathCompression(int[] parents, int c) {
        Stack<Integer> stk = new Stack<>();
        while (parents[c] != c) {
            stk.push(c);
            c = parents[c];
        }
        while (!stk.isEmpty()) {
            int n = stk.pop();
            parents[n] = c;
        }
        return c;
    }

    public static String smallestEquivalentString(String A, String B, String S) {
        int L = Math.min(A.length(), B.length());
        int[] parents = new int[26];
        for (int i = 0; i < parents.length; ++i)
            parents[i] = i;
        for (int i = 0; i < L; ++i) {
            int a = A.charAt(i) - 'a';
            int b = B.charAt(i) - 'a';
            int root1 = findRootPathCompression(parents, a);
            int root2 = findRootPathCompression(parents, b);
            if (root1 != root2) {
                // Make sure root is always the smallest
                if (root1 < root2)
                    parents[root2] = root1;
                else
                    parents[root1] = root2;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); ++i)
            sb.append((char)('a' + findRootPathCompression(parents, S.charAt(i)-'a')));
        return sb.toString();
    }
}
